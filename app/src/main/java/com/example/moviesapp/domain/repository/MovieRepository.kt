package com.example.moviesapp.domain.repository

import androidx.paging.PagingData
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.data.dto.MovieResponseDTO
import com.example.moviesapp.domain.model.CastingUiModel
import com.example.moviesapp.domain.model.MovieDetailUiModel
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.model.ReviewUiModel
import com.example.moviesapp.domain.model.TrailerUiModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getNowPlaying(): Flow<NetworkResponseState<List<MovieUiModel>>>

    suspend fun getPopularMovie(): Flow<PagingData<MovieUiModel>>

    suspend fun getTopRatedMovie(): Flow<PagingData<MovieUiModel>>

    suspend fun getSearchMovies(query: String): Flow<PagingData<MovieUiModel>>

    suspend fun getMovieDetail(id: Int): Flow<NetworkResponseState<MovieDetailUiModel>>

    suspend fun getMovieCasting(id: Int): Flow<NetworkResponseState<List<CastingUiModel>>>

    suspend fun getMovieTrailer(id: Int): Flow<NetworkResponseState<List<TrailerUiModel>>>

    suspend fun getMovieReviews(id: Int): Flow<NetworkResponseState<List<ReviewUiModel>>>

    suspend fun getMovieRecommendations(id: Int): Flow<NetworkResponseState<List<MovieUiModel>>>
}