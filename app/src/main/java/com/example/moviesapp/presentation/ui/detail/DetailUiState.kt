package com.example.moviesapp.presentation.ui.detail

import com.example.moviesapp.domain.model.CastingUiModel
import com.example.moviesapp.domain.model.MovieDetailUiModel
import com.example.moviesapp.domain.model.MovieUiModel

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class SuccessDetailMovie(val data: MovieDetailUiModel) : DetailUiState()
    data class SuccessDetailCasting(val castData: List<CastingUiModel>) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}