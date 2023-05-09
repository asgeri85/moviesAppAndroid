package com.example.moviesapp.presentation.ui.detail

import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.model.ReviewUiModel

sealed class RecommendationsUiState {
    object Loading : RecommendationsUiState()
    data class SuccessRecommendations(val data: List<MovieUiModel>?) : RecommendationsUiState()
    data class Error(val message: String) : RecommendationsUiState()
}
