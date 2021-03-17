package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.ItemAnimeBinding


class GenreListAdapter(
        private val listener: onItemClickListener
) : PagingDataAdapter<Anime, GenreListAdapter.GenreViewHolder>(GenreDiffCallback){

    val LOADING_ITEM = 0
    val ANIME_ITEM = 1
    
    inner class GenreViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(item: Anime) {
            binding.apply {
                ivImage.load(item.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    error(R.drawable.ic_image_error)
                }

                tvScore.text = item.score.toString()
                tvTitle.text = item.title
            }
        }
    }

    companion object {
        private val GenreDiffCallback = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.malId == newItem.malId
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) ANIME_ITEM else LOADING_ITEM
    }

    interface onItemClickListener {
        fun onItemClick(anime: Anime)
    }
}