package com.example.moviesapp.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentMoreLikeDetailBinding
import com.example.moviesapp.presentation.ui.detail.adapters.RecommendationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreLikeDetailFragment(private val id: Int) : Fragment(R.layout.fragment_more_like_detail) {

    private val binding: FragmentMoreLikeDetailBinding by viewBinding(FragmentMoreLikeDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private val recommendationsAdapter = RecommendationsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        viewModel.getRecommendations(id)
        observeState()
    }

    private fun observeState() {
        viewModel.recommendationsUiState.observe(viewLifecycleOwner) {
            when (it) {
                is RecommendationsUiState.SuccessRecommendations -> {
                    recommendationsAdapter.differ.submitList(it.data)
                    binding.moreLikeLoading.gone()
                }

                is RecommendationsUiState.Error -> {
                    binding.moreLikeLoading.gone()
                    binding.rvRecommendations.gone()
                    binding.moreLikeEmpty.visible()
                }

                is RecommendationsUiState.Loading -> {
                    binding.moreLikeLoading.visible()
                }
            }
        }
    }

    private fun setRecycler() {
        binding.rvRecommendations.adapter = recommendationsAdapter
    }

}