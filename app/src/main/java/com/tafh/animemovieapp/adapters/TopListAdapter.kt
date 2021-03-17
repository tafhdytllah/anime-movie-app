package com.tafh.animemovieapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.ItemAnimeBinding


class TopListAdapter(
        private val listener: onItemClickListener
) : PagingDataAdapter<Anime, TopListAdapter.TopViewHolder>(TopDiffCallback) {

    val LOADING_ITEM = 0
    val ANIME_ITEM = 1

    companion object {
        private val TopDiffCallback = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.malId == newItem.malId
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TopViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
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

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("ADAPTER", "$position, $itemCount")
        return if(position == itemCount) ANIME_ITEM else LOADING_ITEM
    }


    interface onItemClickListener {
        fun onItemClick(anime: Anime)
    }




}
