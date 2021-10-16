package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tafh.animemovieapp.data.model.GenreList
import com.tafh.animemovieapp.databinding.ItemGenreBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var genreList = emptyList<GenreList>()
    private val max_item = 20

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genreList: GenreList) {
            binding.tvItemTitle.text = genreList.name
            binding.root.setOnClickListener {
                itemClickCallback?.onItemClick(genreList)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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

    fun setData(list: List<GenreList>) {
        this.genreList = list
        notifyDataSetChanged()
    }

    private var itemClickCallback: onItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: onItemClickCallback) {
        this.itemClickCallback = onItemClickCallback
    }

    interface onItemClickCallback {
        fun onItemClick(genreList: GenreList)
    }


}