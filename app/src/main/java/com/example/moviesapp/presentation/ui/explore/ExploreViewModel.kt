package com.example.moviesapp.presentation.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.useCase.PopularMoviesUseCase
import com.example.moviesapp.domain.useCase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _explorePagingData = MutableLiveData<PagingData<MovieUiModel>>(PagingData.empty())
    val explorePagingData: LiveData<PagingData<MovieUiModel>> get() = _explorePagingData

    init {
        getMovieData()
    }

    fun getMovieData() {
        viewModelScope.launch {
            popularMoviesUseCase().cachedIn(viewModelScope).collectLatest {
                _explorePagingData.postValue(it)
            }
        }
    }

    fun searchMovieData(query: String) {
        viewModelScope.launch {
            searchMovieUseCase(query).cachedIn(viewModelScope).collectLatest {
                _explorePagingData.postValue(it)
            }
        }
    }
}