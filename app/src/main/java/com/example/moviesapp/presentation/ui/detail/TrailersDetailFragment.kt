package com.example.moviesapp.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentTrailersDetailBinding
import com.example.moviesapp.presentation.ui.detail.adapters.TrailerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailersDetailFragment(private val id: Int) : Fragment(R.layout.fragment_trailers_detail) {

    private val binding: FragmentTrailersDetailBinding by viewBinding(FragmentTrailersDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private val trailerAdapter = TrailerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        viewModel.getTrailer(id)
        observeState()

        trailerAdapter.onItemClick = {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToYoutubePlayerFragment(
                    it
                )
            )
        }

    }

    private fun observeState() {
        viewModel.trailerUiState.observe(viewLifecycleOwner) {
            when (it) {
                is TrailerUiState.SuccessTrail -> {
                    trailerAdapter.differ.submitList(it.data)
                    binding.trailerLoading.gone()
                }

                is TrailerUiState.Error -> {
                    binding.trailerLoading.gone()
                    binding.rvTrailer.gone()
                    binding.trailerEmpty.visible()
                }

                is TrailerUiState.Loading -> {
                    binding.trailerLoading.visible()
                }
            }
        }
    }

    private fun setRecycler() {
        binding.rvTrailer.adapter = trailerAdapter
    }

}