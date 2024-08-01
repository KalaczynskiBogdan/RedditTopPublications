package com.example.reddittoppublications.ui.image

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.reddittoppublications.databinding.FragmentImageFullBinding
import com.example.reddittoppublications.urlCorrect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFullFragment : Fragment() {
    private var _binding: FragmentImageFullBinding? = null
    private val binding get() = _binding!!

    private var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageFullBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        receiveImage()
        setImage()

    }

    private fun setImage() {
        Glide.with(this)
            .load(url)
            .into(binding.ivImage)
    }

    private fun receiveImage() {
        val receivedUrl = arguments?.getString(ARG_IMAGE_URL)
        if (receivedUrl != null) {
            url = urlCorrect(receivedUrl)
        }
    }

    private fun setListeners() {
        binding.apply {
            ivImage.setOnClickListener {
                btnSaveImage.visibility = View.GONE
            }
            ivImage.setOnLongClickListener {
                btnSaveImage.visibility = View.VISIBLE
                true
            }
            btnSaveImage.setOnClickListener {
                val filename = "IMG_${System.currentTimeMillis()}.jpg"
                downloadImageNew(filename, url)
            }
        }
    }

    private fun downloadImageNew(filename: String, downloadUrlOfImage: String) {
        try {
            val dm = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "$filename.jpg")

            dm.enqueue(request)
            Toast.makeText(requireContext(), "Image download started.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Image download failed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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