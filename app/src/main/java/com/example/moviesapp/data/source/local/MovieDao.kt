package com.example.moviesapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.data.dto.local.FavoritesLocalDTO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(favoritesLocalDTO: FavoritesLocalDTO)

    @Query("SELECT * FROM favorites")
    suspend fun getFavoritesLocal(): List<FavoritesLocalDTO>
}