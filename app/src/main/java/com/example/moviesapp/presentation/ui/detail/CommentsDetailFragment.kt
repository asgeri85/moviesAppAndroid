package com.example.moviesapp.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentCommentsDetailBinding
import com.example.moviesapp.presentation.ui.detail.adapters.ReviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsDetailFragment(private val id: Int) : Fragment(R.layout.fragment_comments_detail) {

    private val binding: FragmentCommentsDetailBinding by viewBinding(FragmentCommentsDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private val reviewAdapter = ReviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        viewModel.getReviews(id)
        observeState()
    }

    private fun observeState() {
        viewModel.reviewUiState.observe(viewLifecycleOwner) {
            when (it) {
                is ReviewUiState.SuccessReviews -> {
                    reviewAdapter.differ.submitList(it.data)
                    binding.commentsLoading.gone()
                }

                is ReviewUiState.Error -> {
                    binding.commentsLoading.gone()
                    binding.rvReviewDetail.gone()
                    binding.imageViewCommentsEpmty.visible()
                }

                is ReviewUiState.Loading -> {
                    binding.commentsLoading.visible()
                }
            }
        }
    }

    private fun setRecycler() {
        binding.rvReviewDetail.adapter = reviewAdapter
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.rvReviewDetail.addItemDecoration(dividerItemDecoration)
    }
}