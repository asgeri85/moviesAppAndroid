package com.example.moviesapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.data.dto.FavoritesDTO

@Database(
    entities = [FavoritesDTO::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}