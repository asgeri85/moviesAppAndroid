package com.example.moviesapp.domain.model

import com.example.moviesapp.data.dto.GenreDTO

data class MovieUiModel(
    val id: Int?,
    val title: String?,
    val posterImage: String?,
    val backImage: String?,
    val vote: Double?,
) {

    fun rateFormat(): String {
        return String.format("%.1f", vote)
    }

}