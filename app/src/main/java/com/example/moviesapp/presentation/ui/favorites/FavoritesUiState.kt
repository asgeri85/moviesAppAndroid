package com.example.moviesapp.presentation.ui.favorites

import com.example.moviesapp.data.dto.local.FavoritesLocalDTO

sealed class FavoritesUiState {
    object Loading : FavoritesUiState()
    data class SuccessNowPlaying(val data: List<FavoritesLocalDTO>) : FavoritesUiState()
    data class Error(val message: String) : FavoritesUiState()
}