package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.ItemAnimeBinding


class TopAdapter : ListAdapter<Anime, TopAdapter.TopViewHolder>(TopDiffCallback) {

    val MAX_ITEM = 10

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


        fun bind(item: Anime) {
            binding.apply {
                ivImage.load(item.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    error(R.drawable.ic_image_error)
                }

                tvScore.text = item.score.toString()
                tvTitle.text = item.title

                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        
                        if (getItem(position) != null) {
                            itemClickCallback?.onItemClick(item)
                        }

                    }
                }
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

    override fun getItemCount(): Int {
        val value: Int
        val listSize = currentList.size

        if (listSize > MAX_ITEM) {
            value = MAX_ITEM
        } else {
            value = listSize
        }

        return value
    }

    private var itemClickCallback: onItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: onItemClickCallback) {
        this.itemClickCallback = onItemClickCallback
    }

    interface onItemClickCallback {
        fun onItemClick(anime: Anime)
    }




}