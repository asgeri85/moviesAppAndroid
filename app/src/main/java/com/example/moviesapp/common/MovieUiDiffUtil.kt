package com.example.moviesapp.common

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.model.MovieUiModel

class MovieUiDiffUtil(
    private val oldMovieList: List<MovieUiModel>,
    private val newMovieList: List<MovieUiModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldMovieList.size
    }

    override fun getNewListSize(): Int {
        return newMovieList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList[oldItemPosition].id == newMovieList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList[oldItemPosition] == newMovieList[newItemPosition]
    }
}