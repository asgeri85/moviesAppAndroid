package com.example.moviesapp.common

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.model.MovieUiModel

object ItemDiffUtilCallback : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }
}