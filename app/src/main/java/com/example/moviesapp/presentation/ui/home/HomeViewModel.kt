package com.example.moviesapp.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.useCase.NowPlayingMovieUseCase
import com.example.moviesapp.domain.useCase.PopularMoviesUseCase
import com.example.moviesapp.domain.useCase.TopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val nowPlayingMovieUseCase: NowPlayingMovieUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedUseCase: TopRatedMoviesUseCase
) : ViewModel() {

    private val _homeUiStateData = MutableLiveData<HomeUiState>()
    val homeUiStateData: LiveData<HomeUiState> get() = _homeUiStateData

    private val _pagingDataPopular = MutableLiveData<PagingData<MovieUiModel>>(PagingData.empty())
    val pagingDataPopular: LiveData<PagingData<MovieUiModel>> get() = _pagingDataPopular

    private val _pagingDataTopRated = MutableLiveData<PagingData<MovieUiModel>>(PagingData.empty())
    val pagingDataTopRated: LiveData<PagingData<MovieUiModel>> get() = _pagingDataTopRated

    init {
        getNowPlayingMovies()
        getPopularMovies()
        getTopRatedMovies()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            nowPlayingMovieUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Loading -> _homeUiStateData.postValue(HomeUiState.Loading)
                    is NetworkResponseState.Success -> _homeUiStateData.postValue(it.result?.let { it1 ->
                        HomeUiState.SuccessNowPlaying(
                            it1
                        )
                    })

                    is NetworkResponseState.Error -> _homeUiStateData.postValue(it.exception.message?.let { it1 ->
                        HomeUiState.Error(
                            it1
                        )
                    })
                }
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            popularMoviesUseCase().cachedIn(viewModelScope).collectLatest {
                _pagingDataPopular.postValue(it)
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            topRatedUseCase().cachedIn(viewModelScope).collectLatest {
                _pagingDataTopRated.postValue(it)
            }
        }
    }

}