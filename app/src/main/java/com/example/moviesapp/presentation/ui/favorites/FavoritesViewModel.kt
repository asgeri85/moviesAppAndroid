package com.example.moviesapp.presentation.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.domain.useCase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _favoritesUiState = MutableLiveData<FavoritesUiState>()
    val favoritesUiState: LiveData<FavoritesUiState> get() = _favoritesUiState

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Loading -> _favoritesUiState.postValue(FavoritesUiState.Loading)
                    is NetworkResponseState.Success -> _favoritesUiState.postValue(it.result?.let { it1 ->
                        FavoritesUiState.SuccessNowPlaying(
                            it1
                        )
                    })

                    is NetworkResponseState.Error -> _favoritesUiState.postValue(it.exception.message?.let { it1 ->
                        FavoritesUiState.Error(
                            it1
                        )
                    })
                }
            }
        }
    }

}