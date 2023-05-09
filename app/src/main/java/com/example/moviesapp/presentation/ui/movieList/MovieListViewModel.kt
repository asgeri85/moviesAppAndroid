package com.example.moviesapp.presentation.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.useCase.PopularMoviesUseCase
import com.example.moviesapp.domain.useCase.TopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase
) : ViewModel() {

    private val _pagingDataMovies = MutableLiveData<PagingData<MovieUiModel>>(PagingData.empty())
    val pagingDataMovies: LiveData<PagingData<MovieUiModel>> get() = _pagingDataMovies

    fun getPopularMovies() {
        viewModelScope.launch {
            popularMoviesUseCase().cachedIn(viewModelScope).collectLatest {
                _pagingDataMovies.postValue(it)
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            topRatedMoviesUseCase().cachedIn(viewModelScope).collectLatest {
                _pagingDataMovies.postValue(it)
            }
        }
    }
}