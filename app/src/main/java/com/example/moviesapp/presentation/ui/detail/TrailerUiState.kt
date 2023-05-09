package com.example.moviesapp.presentation.ui.detail

import com.example.moviesapp.domain.model.CastingUiModel
import com.example.moviesapp.domain.model.MovieDetailUiModel
import com.example.moviesapp.domain.model.TrailerUiModel

sealed class TrailerUiState {
    object Loading : TrailerUiState()
    data class SuccessTrail(val data: List<TrailerUiModel>?) : TrailerUiState()
    data class Error(val message: String) : TrailerUiState()
}