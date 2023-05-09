package com.example.moviesapp.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.dto.GenreDTO
import com.example.moviesapp.databinding.ItemGenresBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    inner class GenreViewHolder(private val itemGenresBinding: ItemGenresBinding) :
        RecyclerView.ViewHolder(itemGenresBinding.root) {
        fun bind(genre: GenreDTO) {
            itemGenresBinding.textView22.text = "${genre.name} ,"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layout = ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object GenreDiffUtilCallback : DiffUtil.ItemCallback<GenreDTO>() {
        override fun areItemsTheSame(oldItem: GenreDTO, newItem: GenreDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreDTO, newItem: GenreDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, GenreDiffUtilCallback)


}