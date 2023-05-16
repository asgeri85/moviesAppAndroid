package com.example.moviesapp.presentation.ui.favorites.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.dto.local.FavoritesLocalDTO
import com.example.moviesapp.databinding.ItemFavoritesBinding

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    inner class FavoritesViewHolder(private val itemFavoritesBinding: ItemFavoritesBinding) :
        RecyclerView.ViewHolder(itemFavoritesBinding.root) {
        fun bind(favoritesLocalDTO: FavoritesLocalDTO) {
            Log.e("sekil", favoritesLocalDTO.image.toString())
            itemFavoritesBinding.favorites = favoritesLocalDTO
            itemFavoritesBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layout =
            ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavoritesViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object FavoritesDiffUtilCallback : DiffUtil.ItemCallback<FavoritesLocalDTO>() {
        override fun areItemsTheSame(
            oldItem: FavoritesLocalDTO,
            newItem: FavoritesLocalDTO
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoritesLocalDTO,
            newItem: FavoritesLocalDTO
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, FavoritesDiffUtilCallback)
}