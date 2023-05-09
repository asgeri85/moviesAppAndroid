package com.example.moviesapp.presentation.ui.profile

import com.example.moviesapp.presentation.ui.auth.AuthUiState
import com.google.firebase.auth.FirebaseUser

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val user: FirebaseUser) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}