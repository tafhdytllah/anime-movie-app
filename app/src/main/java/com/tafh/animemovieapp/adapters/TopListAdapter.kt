package com.tafh.animemovieapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.top.Top
import com.tafh.animemovieapp.databinding.ItemTopListBinding


class TopListAdapter(
        private val listener: onItemClickListener
) : PagingDataAdapter<Top, TopListAdapter.TopViewHolder>(TopDiffCallback) {

    val LOADING_ITEM = 0
    val ANIME_ITEM = 1

    companion object {
        private val TopDiffCallback = object : DiffUtil.ItemCallback<Top>() {
            override fun areItemsTheSame(oldItem: Top, newItem: Top): Boolean {
                return oldItem.malId == newItem.malId
            }

            override fun areContentsTheSame(oldItem: Top, newItem: Top): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TopViewHolder(private val binding: ItemTopListBinding) : RecyclerView.ViewHolder(binding.root) {

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

        fun bind(item: Top) {
            binding.apply {
                ivImage.load(item.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(R.drawable.ic_image)
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
        return TopViewHolder(ItemTopListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("ADAPTER", "$position, $itemCount")
        return if(position == itemCount) ANIME_ITEM else LOADING_ITEM
    }


    interface onItemClickListener {
        fun onItemClick(top: Top)
    }




}
