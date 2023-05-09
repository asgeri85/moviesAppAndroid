package com.example.moviesapp.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.moviesapp.data.dto.GenreDTO
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MovieDetailUiModel(
    val id: Int?,
    val title: String?,
    val about: String?,
    val posterImage: String?,
    val backImage: String?,
    val vote: Double?,
    val date: String?,
    val genres: List<GenreDTO?>?
) {
    fun rateFormat(): String {
        return String.format("%.1f", vote)
    }

}