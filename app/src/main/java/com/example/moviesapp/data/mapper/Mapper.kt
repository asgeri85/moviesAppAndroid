package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.dto.CastDTO
import com.example.moviesapp.data.dto.MovieDetailDTO
import com.example.moviesapp.data.dto.MovieResponseDTO
import com.example.moviesapp.data.dto.ReviewsDTO
import com.example.moviesapp.data.dto.TrailerDTO
import com.example.moviesapp.domain.model.CastingUiModel
import com.example.moviesapp.domain.model.MovieDetailUiModel
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.domain.model.ReviewUiModel
import com.example.moviesapp.domain.model.TrailerUiModel

fun List<MovieResponseDTO?>.toMovieUiModel() = map {
    MovieUiModel(
        id = it?.id,
        title = it?.title,
        posterImage = it?.posterPath,
        backImage = it?.backdropPath,
        vote = it?.voteAverage
    )
}

fun MovieResponseDTO.toMovieUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        title = title,
        posterImage = posterPath,
        backImage = backdropPath,
        vote = voteAverage
    )
}

fun MovieDetailDTO.toMovieDetailUiModel(): MovieDetailUiModel {
    return MovieDetailUiModel(
        id = id,
        title = title,
        posterImage = posterPath,
        backImage = backdropPath,
        vote = voteAverage,
        date = releaseDate,
        about = overview,
        genres = genres
    )
}

fun List<CastDTO?>.toCastingUiModel() = map {
    CastingUiModel(
        id = it?.id,
        name = it?.name,
        character = it?.character,
        image = it?.profilePath
    )
}

fun List<TrailerDTO?>.toTrailerUiModel() = map {
    TrailerUiModel(
        id = it?.id,
        name = it?.name,
        site = it?.site,
        key = it?.key
    )
}

fun List<ReviewsDTO?>.toReviewsUiModel() = map {
    ReviewUiModel(
        id = it?.id,
        name = it?.author,
        image = it?.authorDetails?.avatarPath,
        content = it?.content
    )
}
