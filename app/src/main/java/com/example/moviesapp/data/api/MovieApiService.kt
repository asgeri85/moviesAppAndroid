package com.example.moviesapp.data.api

import com.example.moviesapp.data.dto.CastingDTO
import com.example.moviesapp.data.dto.MovieDetailDTO
import com.example.moviesapp.data.dto.NowPlayingDTO
import com.example.moviesapp.data.dto.PopularMovieDTO
import com.example.moviesapp.data.dto.ReviewsResponseDTO
import com.example.moviesapp.data.dto.TrailerResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingApi(): Response<NowPlayingDTO>

    @GET("movie/popular")
    suspend fun getPopularApi(@Query("page") page: Int): Response<PopularMovieDTO>

    @GET("movie/top_rated")
    suspend fun getTopRatedApi(@Query("page") page: Int): Response<PopularMovieDTO>

    @GET("search/movie")
    suspend fun getSearchMovieApi(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Response<PopularMovieDTO>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): Response<MovieDetailDTO>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") id: Int): Response<CastingDTO>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(@Path("movie_id") id: Int): Response<TrailerResponseDTO>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") id: Int): Response<ReviewsResponseDTO>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(@Path("movie_id") id: Int): Response<PopularMovieDTO>

}