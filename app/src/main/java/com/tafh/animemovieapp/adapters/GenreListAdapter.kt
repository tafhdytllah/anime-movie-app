package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tafh.animemovieapp.data.model.GenreAnime
import com.tafh.animemovieapp.databinding.ItemGenreListBinding

class GenreListAdapter : RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>() {

    private var genreList = emptyList<GenreAnime>()
    private val max_item = 20

    inner class GenreViewHolder(private val binding: ItemGenreListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: GenreAnime) {
            binding.tvItemTitle.text = genre.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(ItemGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int {
        if (genreList.size > max_item) {
            return max_item
        } else {
            return genreList.size
        }
    }

    fun setData(list: List<GenreAnime>) {
        this.genreList = list
        notifyDataSetChanged()
    }


}