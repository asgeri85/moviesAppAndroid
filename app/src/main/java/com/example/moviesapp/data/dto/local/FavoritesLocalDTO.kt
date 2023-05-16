package com.example.moviesapp.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesLocalDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val title: String?,
    val image: String?,
    val vote: Double?
)