package com.example.reddittoppublications.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.reddittoppublications.databinding.FragmentTopPublicationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopPublicationsFragment : Fragment() {
    private val viewModel: TopPublicationsViewModel by viewModels()

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
        viewModel.getList()
        initObservers()
    }

    private fun initObservers() {
        viewModel.topPublicationsLiveData.observe(viewLifecycleOwner) {
            Log.d("result", it.toString())
            val result = it[1]
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): TopPublicationsFragment = TopPublicationsFragment()
    }
}