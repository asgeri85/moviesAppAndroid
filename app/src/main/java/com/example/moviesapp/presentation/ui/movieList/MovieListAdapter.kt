package com.example.moviesapp.presentation.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.common.ItemDiffUtilCallback
import com.example.moviesapp.databinding.ItemMovieListBinding
import com.example.moviesapp.domain.model.MovieUiModel

class MovieListAdapter :
    PagingDataAdapter<MovieUiModel, MovieListAdapter.MovieListViewHolder>(ItemDiffUtilCallback) {
    inner class MovieListViewHolder(private val itemMovieListBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemMovieListBinding.root) {
        fun bind(movieUiModel: MovieUiModel) {
            itemMovieListBinding.movie = movieUiModel
            itemMovieListBinding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val layout =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieListViewHolder(layout)
    }
}