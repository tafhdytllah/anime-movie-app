package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.ItemEpisodeListBinding

class AnimeDayAdapter : RecyclerView.Adapter<AnimeDayAdapter.AnimeDayViewHolder>() {

    val MAX_ITEM = 9

    private var list = emptyList<Anime>()

    inner class AnimeDayViewHolder(private val binding: ItemEpisodeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animeDay: Anime) {
            binding.apply {
                ivImage.load(animeDay.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(R.drawable.ic_image)
                }
                tvTitle.text = animeDay.title
                tvScore.text = animeDay.score.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeDayViewHolder {
        return AnimeDayViewHolder(ItemEpisodeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AnimeDayViewHolder, position: Int) {
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
}