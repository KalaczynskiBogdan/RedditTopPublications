package com.example.reddittoppublications.ui.image

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.reddittoppublications.R
import com.example.reddittoppublications.databinding.FragmentImageFullBinding
import com.example.reddittoppublications.ui.main.TopPublicationsFragment
import com.example.reddittoppublications.urlCorrect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFullFragment : Fragment() {
    private val viewModel: ImageFullViewModel by activityViewModels()

    private var _binding: FragmentImageFullBinding? = null
    private val binding get() = _binding!!

    private var currentPhotoId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageFullBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.wasFragmentActive()){
            val savedImage = viewModel.getSavedPhotoId()
            if (savedImage != null){
                Glide.with(this).load(savedImage).into(binding.ivImage)
            }

        }else{
            receiveImage()
        }

        setListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.imageUrl.observe(viewLifecycleOwner) { url ->
            Glide.with(this).load(url).into(binding.ivImage)
            currentPhotoId = url
            Log.d("curentPhoto", currentPhotoId.toString())
        }
    }

    private fun receiveImage() {
        val receivedUrl = arguments?.getString(ARG_IMAGE_URL)
        if (receivedUrl != null) {
            val url = urlCorrect(receivedUrl)
            viewModel.setUrl(url)
        }
    }

    private fun setListeners() {
        binding.apply {
            ivBack.setOnClickListener {
                val fragment = TopPublicationsFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main, fragment)
                    .commit()
            }
            ivImage.setOnClickListener {
                btnSaveImage.visibility = View.GONE
            }
            ivImage.setOnLongClickListener {
                btnSaveImage.visibility = View.VISIBLE
                true
            }
            btnSaveImage.setOnClickListener {
                val filename = "IMG_${System.currentTimeMillis()}.jpg"
                viewModel.imageUrl.value?.let {
                    viewModel.downloadImage(filename, it)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.setFragmentActive(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setFragmentActive(false)
        _binding = null
    }

    companion object {
        private const val ARG_IMAGE_URL = "URL"
        fun newInstance(imageUrl: String): ImageFullFragment {
            val fragment = ImageFullFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URL, imageUrl)
            fragment.arguments = args
            return fragment
        }
    }
}