package com.example.reddittoppublications.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reddittoppublications.R
import com.example.reddittoppublications.databinding.FragmentTopPublicationsBinding
import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.ui.image.ImageFullFragment
import com.example.reddittoppublications.ui.main.adapter.TopPublicationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopPublicationsFragment : Fragment() {
    private val viewModel: TopPublicationsViewModel by activityViewModels()

    private var _binding: FragmentTopPublicationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopPublicationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        if (savedInstanceState == null) {
            viewModel.getList()
        }
    }

    private fun initObservers() {
        viewModel.topPublicationsLiveData.observe(viewLifecycleOwner) {
            showTopPublicationList(it)
        }
    }

    private fun showTopPublicationList(children: List<Children>) {
        val adapter = TopPublicationsAdapter(object : TopPublicationsAdapter.OnItemClickListener {
            override fun onImageClicked(position: Int, children: Children) {
                val url = children.dataX.preview.images.getOrNull(0)?.source?.url

                val imageFragment = ImageFullFragment.newInstance(url!!)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main, imageFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
        adapter.submitList(children)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.apply {
            rvPublications.layoutManager = layoutManager
            rvPublications.setHasFixedSize(true)
            rvPublications.adapter = adapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}