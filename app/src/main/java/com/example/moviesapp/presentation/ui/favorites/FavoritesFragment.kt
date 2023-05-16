package com.example.moviesapp.presentation.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.databinding.FragmentFavoritesBinding
import com.example.moviesapp.presentation.ui.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel by viewModels<FavoritesViewModel>()
    private val favoritesAdapter = FavoritesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        observeState()
    }

    private fun observeState() {
        viewModel.favoritesUiState.observe(viewLifecycleOwner) {
            when (it) {
                is FavoritesUiState.SuccessNowPlaying -> {
                    favoritesAdapter.differ.submitList(it.data)
                }

                is FavoritesUiState.Error -> {

                }

                is FavoritesUiState.Loading -> {

                }
            }
        }
    }

    private fun setRecycler() {
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.rvFavorites.adapter = favoritesAdapter
        binding.rvFavorites.addItemDecoration(dividerItemDecoration)
    }
}