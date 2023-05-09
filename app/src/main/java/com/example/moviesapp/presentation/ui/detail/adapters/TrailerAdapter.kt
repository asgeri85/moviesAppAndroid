package com.example.moviesapp.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemTrailerBinding
import com.example.moviesapp.domain.model.TrailerUiModel

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    var onItemClick: (id: String) -> Unit = {}

    inner class TrailerViewHolder(private val itemTrailerBinding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(itemTrailerBinding.root) {
        fun bind(trailerUiModel: TrailerUiModel) {
            itemTrailerBinding.trailer = trailerUiModel
            itemTrailerBinding.executePendingBindings()

            itemTrailerBinding.root.setOnClickListener {
                trailerUiModel.key?.let { it1 -> onItemClick(it1) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val layout = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrailerViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object TrailerDiffUtilCallback : DiffUtil.ItemCallback<TrailerUiModel>() {
        override fun areItemsTheSame(oldItem: TrailerUiModel, newItem: TrailerUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrailerUiModel, newItem: TrailerUiModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, TrailerDiffUtilCallback)
}