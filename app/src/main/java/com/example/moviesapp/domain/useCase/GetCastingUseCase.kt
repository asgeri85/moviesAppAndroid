package com.example.moviesapp.domain.useCase

import com.example.moviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetCastingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(id: Int) = movieRepository.getMovieCasting(id)

}