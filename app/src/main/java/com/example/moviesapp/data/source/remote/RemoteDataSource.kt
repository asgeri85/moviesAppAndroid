package com.example.moviesapp.data.source.remote

import androidx.paging.PagingData
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.data.dto.CastingDTO
import com.example.moviesapp.data.dto.MovieDetailDTO
import com.example.moviesapp.data.dto.MovieResponseDTO
import com.example.moviesapp.data.dto.NowPlayingDTO
import com.example.moviesapp.data.dto.PopularMovieDTO
import com.example.moviesapp.data.dto.ReviewsResponseDTO
import com.example.moviesapp.data.dto.TrailerResponseDTO
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getNowPlayingData(): NetworkResponseState<NowPlayingDTO>

    suspend fun getPopularMovies(): Flow<PagingData<MovieResponseDTO>>

    suspend fun getTopRatedMovies(): Flow<PagingData<MovieResponseDTO>>

    suspend fun getSearchMovies(query: String): Flow<PagingData<MovieResponseDTO>>

    suspend fun getMovieDetail(id: Int): NetworkResponseState<MovieDetailDTO>

    suspend fun getMovieCredits(id: Int): NetworkResponseState<CastingDTO>

    suspend fun getMovieTrailer(id: Int): NetworkResponseState<TrailerResponseDTO>

    suspend fun getMovieReviews(id: Int): NetworkResponseState<ReviewsResponseDTO>

    suspend fun getMovieRecommendations(id: Int): NetworkResponseState<PopularMovieDTO>
}