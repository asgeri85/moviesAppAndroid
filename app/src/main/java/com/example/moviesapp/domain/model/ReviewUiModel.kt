package com.example.moviesapp.domain.model

import com.example.moviesapp.data.dto.AuthorDetails
import com.squareup.moshi.Json

data class ReviewUiModel(
    val id: String?,
    val name: String?,
    val image: String?,
    val content: String?,
)