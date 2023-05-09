package com.example.moviesapp.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.domain.useCase.GetCastingUseCase
import com.example.moviesapp.domain.useCase.GetMovieDetailUseCase
import com.example.moviesapp.domain.useCase.GetMovieReviewsUseCase
import com.example.moviesapp.domain.useCase.GetRecommendationsUseCase
import com.example.moviesapp.domain.useCase.GetTrailerMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getCastingUseCase: GetCastingUseCase,
    private val getTrailerMovieUseCase: GetTrailerMovieUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    private val _detailUiState = MutableLiveData<DetailUiState>()
    val detailUiState: LiveData<DetailUiState> get() = _detailUiState

    private val _trailerUiState = MutableLiveData<TrailerUiState>()
    val trailerUiState: LiveData<TrailerUiState> get() = _trailerUiState

    private val _reviewUiState = MutableLiveData<ReviewUiState>()
    val reviewUiState: LiveData<ReviewUiState> get() = _reviewUiState

    private val _recommendationsUiState = MutableLiveData<RecommendationsUiState>()
    val recommendationsUiState: LiveData<RecommendationsUiState> get() = _recommendationsUiState

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase(id).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> _detailUiState.value =
                        it.result?.let { it1 -> DetailUiState.SuccessDetailMovie(it1) }

                    is NetworkResponseState.Error -> _detailUiState.value =
                        it.exception.message?.let { it1 -> DetailUiState.Error(it1) }

                    is NetworkResponseState.Loading -> _detailUiState.value = DetailUiState.Loading
                }
            }
        }
    }

    fun getCastingDetail(id: Int) {
        viewModelScope.launch {
            getCastingUseCase(id).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> _detailUiState.value =
                        it.result?.let { it1 -> DetailUiState.SuccessDetailCasting(it1) }

                    is NetworkResponseState.Error -> _detailUiState.value =
                        it.exception.message?.let { it1 -> DetailUiState.Error(it1) }

                    is NetworkResponseState.Loading -> _detailUiState.value = DetailUiState.Loading
                }
            }
        }
    }

    fun getTrailer(id: Int) {
        viewModelScope.launch {
            getTrailerMovieUseCase(id).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> _trailerUiState.postValue(
                        TrailerUiState.SuccessTrail(
                            it.result
                        )
                    )

                    is NetworkResponseState.Error -> _trailerUiState.postValue(it.exception.message?.let { it1 ->
                        TrailerUiState.Error(
                            it1
                        )
                    })

                    is NetworkResponseState.Loading -> _trailerUiState.postValue(TrailerUiState.Loading)
                }
            }
        }
    }

    fun getReviews(id: Int) {
        viewModelScope.launch {
            getMovieReviewsUseCase(id).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> _reviewUiState.postValue(
                        ReviewUiState.SuccessReviews(
                            it.result
                        )
                    )

                    is NetworkResponseState.Error -> _reviewUiState.postValue(it.exception.message?.let { it1 ->
                        ReviewUiState.Error(
                            it1
                        )
                    })

                    is NetworkResponseState.Loading -> _reviewUiState.postValue(ReviewUiState.Loading)
                }
            }
        }
    }


    fun getRecommendations(id: Int) {
        viewModelScope.launch {
            getRecommendationsUseCase(id).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        _recommendationsUiState.postValue(
                            RecommendationsUiState.SuccessRecommendations(it.result)

                        )
                        Log.e("gelenData", it.result.toString())
                    }

                    is NetworkResponseState.Error -> _recommendationsUiState.postValue(it.exception.message?.let { it1 ->
                        RecommendationsUiState.Error(
                            it1
                        )
                    })

                    is NetworkResponseState.Loading -> _recommendationsUiState.postValue(
                        RecommendationsUiState.Loading
                    )
                }
            }
        }
    }

}