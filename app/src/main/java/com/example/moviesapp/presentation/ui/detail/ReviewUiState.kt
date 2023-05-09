package com.example.moviesapp.presentation.ui.detail

import com.example.moviesapp.domain.model.ReviewUiModel
import com.example.moviesapp.domain.model.TrailerUiModel

sealed class ReviewUiState {
    object Loading : ReviewUiState()
    data class SuccessReviews(val data: List<ReviewUiModel>?) : ReviewUiState()
    data class Error(val message: String) : ReviewUiState()
}
