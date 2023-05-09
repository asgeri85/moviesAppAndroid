package com.example.moviesapp.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.domain.useCase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<ProfileUiState>(ProfileUiState.Loading)
    val userInfo: LiveData<ProfileUiState> get() = _userInfo

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> _userInfo.postValue(it.result?.let { it1 ->
                        ProfileUiState.Success(
                            it1
                        )
                    })

                    is NetworkResponseState.Error -> _userInfo.postValue(it.exception.message?.let { it1 ->
                        ProfileUiState.Error(
                            it1
                        )
                    })

                    is NetworkResponseState.Loading -> _userInfo.postValue(ProfileUiState.Loading)
                }
            }
        }
    }

}