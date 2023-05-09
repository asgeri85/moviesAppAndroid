package com.example.moviesapp.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.common.utils.SharedPrefManager
import com.example.moviesapp.domain.useCase.LoginUseCase
import com.example.moviesapp.domain.useCase.LogoutUseCase
import com.example.moviesapp.domain.useCase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sp: SharedPrefManager
) : ViewModel() {

    private val _authResult = MutableLiveData<AuthUiState>()
    val authResult: LiveData<AuthUiState> get() = _authResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _authResult.postValue(AuthUiState.Success)
                        it.result?.user?.uid?.let { it1 -> sp.saveToken(it1) }
                    }

                    is NetworkResponseState.Error -> {
                        _authResult.postValue(AuthUiState.Error(it.exception.message.toString()))
                    }

                    is NetworkResponseState.Loading -> {
                        _authResult.postValue(AuthUiState.Loading)
                    }
                }
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            registerUseCase(email, password).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _authResult.postValue(AuthUiState.Success)
                    }

                    is NetworkResponseState.Error -> {
                        _authResult.postValue(AuthUiState.Error(it.exception.message.toString()))
                    }

                    is NetworkResponseState.Loading -> {
                        _authResult.postValue(AuthUiState.Loading)
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        sp.saveToken(null)
                        _authResult.postValue(AuthUiState.Success)
                    }

                    is NetworkResponseState.Error -> _authResult.postValue(it.exception.message?.let { it1 ->
                        AuthUiState.Error(
                            it1
                        )
                    })

                    is NetworkResponseState.Loading -> _authResult.postValue(AuthUiState.Loading)
                }
            }
        }
    }

}