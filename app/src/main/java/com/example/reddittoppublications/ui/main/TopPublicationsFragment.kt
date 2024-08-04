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
class TopPublicationsFragment : Fragment(), TopPublicationsAdapter.OnItemClickListener {
    private val viewModel: TopPublicationsViewModel by activityViewModels()

    private var publicationsAdapter: TopPublicationsAdapter? = null

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
        setupAdapter()

        initObservers()
    }

    private fun initObservers() {
        viewModel.publicationsList.observe(viewLifecycleOwner) {
            publicationsAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupAdapter() {
        publicationsAdapter = TopPublicationsAdapter(this)
        binding.rvPublications.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = publicationsAdapter
        }
    }

    override fun onDestroyView() {
        publicationsAdapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onImageClicked(position: Int, children: Children) {
        val url = viewModel.onImageClicked(children)

        if (url != null) {
            val imageFragment = ImageFullFragment.newInstance(url)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main, imageFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}