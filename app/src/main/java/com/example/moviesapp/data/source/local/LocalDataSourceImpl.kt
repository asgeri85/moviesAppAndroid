package com.example.moviesapp.data.source.local

import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.data.dto.local.FavoritesLocalDTO
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) : LocalDataSource {
    override suspend fun addFavorites(favoritesLocalDTO: FavoritesLocalDTO) {
        movieDao.addFavorites(favoritesLocalDTO)
    }

    override suspend fun getFavorites(): NetworkResponseState<List<FavoritesLocalDTO>> {
        return try {
            NetworkResponseState.Success(movieDao.getFavoritesLocal())
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }
}