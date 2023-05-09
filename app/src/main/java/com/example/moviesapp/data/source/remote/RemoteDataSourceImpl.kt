package com.example.moviesapp.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesapp.common.MovieTypeEnum
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.dto.CastingDTO
import com.example.moviesapp.data.dto.MovieDetailDTO
import com.example.moviesapp.data.dto.MovieResponseDTO
import com.example.moviesapp.data.dto.NowPlayingDTO
import com.example.moviesapp.data.dto.PopularMovieDTO
import com.example.moviesapp.data.dto.ReviewsResponseDTO
import com.example.moviesapp.data.dto.TrailerResponseDTO
import com.example.moviesapp.data.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: MovieApiService
) : RemoteDataSource {

    override suspend fun getNowPlayingData(): NetworkResponseState<NowPlayingDTO> {
        return try {
            val response = api.getNowPlayingApi().body()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getPopularMovies(): Flow<PagingData<MovieResponseDTO>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            MoviePagingSource(api, MovieTypeEnum.POPULAR_MOVIES)
        }
    ).flow

    override suspend fun getTopRatedMovies(): Flow<PagingData<MovieResponseDTO>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            MoviePagingSource(api, MovieTypeEnum.TOP_RATED_MOVIES)
        }
    ).flow

    override suspend fun getSearchMovies(query: String): Flow<PagingData<MovieResponseDTO>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            MoviePagingSource(api, MovieTypeEnum.SEARCH_MOVIES, query)
        }
    ).flow

    override suspend fun getMovieDetail(id: Int): NetworkResponseState<MovieDetailDTO> {
        return try {
            val response = api.getMovieDetail(id).body()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getMovieCredits(id: Int): NetworkResponseState<CastingDTO> {
        return try {
            val response = api.getMovieCredits(id).body()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getMovieTrailer(id: Int): NetworkResponseState<TrailerResponseDTO> {
        return try {
            val response = api.getMovieTrailer(id).body()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getMovieReviews(id: Int): NetworkResponseState<ReviewsResponseDTO> {
        return try {
            val response = api.getMovieReviews(id).body()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getMovieRecommendations(id: Int): NetworkResponseState<PopularMovieDTO> {
        return try {
            val response = api.getMovieRecommendations(id).body()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

}