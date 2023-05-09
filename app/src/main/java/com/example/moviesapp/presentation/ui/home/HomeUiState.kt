package com.example.moviesapp.presentation.ui.home

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.MovieUiModel

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class SuccessNowPlaying(val data: List<MovieUiModel>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}