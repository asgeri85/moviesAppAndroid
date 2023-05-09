package com.example.moviesapp.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.data.mapper.toCastingUiModel
import com.example.moviesapp.data.mapper.toMovieDetailUiModel
import com.example.moviesapp.data.mapper.toMovieUiModel
import com.example.moviesapp.data.mapper.toReviewsUiModel
import com.example.moviesapp.data.mapper.toTrailerUiModel
import com.example.moviesapp.data.source.remote.RemoteDataSource
import com.example.moviesapp.domain.model.CastingUiModel
import com.example.moviesapp.domain.model.MovieDetailUiModel
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.model.ReviewUiModel
import com.example.moviesapp.domain.model.TrailerUiModel
import com.example.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getNowPlaying(): Flow<NetworkResponseState<List<MovieUiModel>>> = flow {
        emit(NetworkResponseState.Loading)
        when (val response = remoteDataSource.getNowPlayingData()) {
            is NetworkResponseState.Success -> {
                emit(NetworkResponseState.Success(response.result?.results?.toMovieUiModel()))
            }

            is NetworkResponseState.Error -> {
                emit(NetworkResponseState.Error(response.exception))
            }

            else -> {}
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPopularMovie(): Flow<PagingData<MovieUiModel>> = flow {
        remoteDataSource.getPopularMovies().map { pagingData ->
            pagingData.map { it.toMovieUiModel() }
        }.collect {
            emit(it)
        }
    }

    override suspend fun getTopRatedMovie(): Flow<PagingData<MovieUiModel>> = flow {
        remoteDataSource.getTopRatedMovies().map { pagingData ->
            pagingData.map { it.toMovieUiModel() }
        }.collect {
            emit(it)
        }
    }

    override suspend fun getSearchMovies(query: String): Flow<PagingData<MovieUiModel>> = flow {
        remoteDataSource.getSearchMovies(query).map { pagingData ->
            pagingData.map { it.toMovieUiModel() }
        }.collect {
            emit(it)
        }
    }

    override suspend fun getMovieDetail(id: Int): Flow<NetworkResponseState<MovieDetailUiModel>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getMovieDetail(id)) {
                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(response.result?.toMovieDetailUiModel()))
                }

                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(response.exception))
                }

                else -> {}
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getMovieCasting(id: Int): Flow<NetworkResponseState<List<CastingUiModel>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getMovieCredits(id)) {
                is NetworkResponseState.Success -> emit(NetworkResponseState.Success(response.result?.cast?.toCastingUiModel()))
                is NetworkResponseState.Error -> emit(NetworkResponseState.Error(response.exception))
                else -> {}
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getMovieTrailer(id: Int): Flow<NetworkResponseState<List<TrailerUiModel>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getMovieTrailer(id)) {
                is NetworkResponseState.Success -> emit(NetworkResponseState.Success(response.result?.results?.toTrailerUiModel()))
                is NetworkResponseState.Error -> emit(NetworkResponseState.Error(response.exception))
                else -> {}
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getMovieReviews(id: Int): Flow<NetworkResponseState<List<ReviewUiModel>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getMovieReviews(id)) {
                is NetworkResponseState.Success -> emit(NetworkResponseState.Success(response.result?.results?.toReviewsUiModel()))
                is NetworkResponseState.Error -> emit(NetworkResponseState.Error(response.exception))
                else -> {}
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getMovieRecommendations(id: Int): Flow<NetworkResponseState<List<MovieUiModel>>> =
        flow<NetworkResponseState<List<MovieUiModel>>> {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getMovieRecommendations(id)) {
                is NetworkResponseState.Success -> emit(NetworkResponseState.Success(response.result?.results?.toMovieUiModel()))
                is NetworkResponseState.Error -> emit(NetworkResponseState.Error(response.exception))
                else -> {}
            }
        }.flowOn(Dispatchers.IO)

}