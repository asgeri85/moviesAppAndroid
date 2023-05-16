package com.example.moviesapp.domain.useCase

import com.example.moviesapp.data.dto.local.FavoritesLocalDTO
import com.example.moviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class AddFavoritesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(favoritesLocalDTO: FavoritesLocalDTO) =
        movieRepository.addFavorites(favoritesLocalDTO)
}