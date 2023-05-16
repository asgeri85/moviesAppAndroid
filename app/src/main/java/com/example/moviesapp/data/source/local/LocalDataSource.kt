package com.example.moviesapp.data.source.local

import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.data.dto.local.FavoritesLocalDTO

interface LocalDataSource {

    suspend fun addFavorites(favoritesLocalDTO: FavoritesLocalDTO)

    suspend fun getFavorites(): NetworkResponseState<List<FavoritesLocalDTO>>

}