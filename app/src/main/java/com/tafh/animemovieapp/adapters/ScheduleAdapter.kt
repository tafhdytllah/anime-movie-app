package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.ItemAnimeBinding

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    val MAX_ITEM = 9

    private var list = emptyList<Anime>()

    inner class ScheduleViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.apply {
                ivImage.load(anime.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    error(R.drawable.ic_image_error)
                }
                tvTitle.text = anime.title
                tvScore.text = anime.score.toString()

                root.setOnClickListener {
                    itemClicakCallback?.onItemClick(anime)
                }
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        val value: Int
        val listSize = list.size

        if (listSize > MAX_ITEM) {
            value = MAX_ITEM
        } else {
            value = listSize
        }

        return value
    }

    fun setData(list: List<Anime>?) {
        if (list != null) {
            this.list = list
        }
        notifyDataSetChanged()
    }

    private var itemClicakCallback: onItemClickCallback? = null

    fun setOnItemClickListener(onItemClickCallback: onItemClickCallback) {
        this.itemClicakCallback = onItemClickCallback
    }

    interface onItemClickCallback {
        fun onItemClick(anime: Anime)
    }

}