package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.top.Top
import com.tafh.animemovieapp.databinding.ItemTopListBinding


class TopListAdapter : RecyclerView.Adapter<TopListAdapter.TopViewHolder>() {


    private var list = emptyList<Top>()
    private val max_item = 20

    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    inner class TopViewHolder(private val binding: ItemTopListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(top: Top) {
            binding.apply {
                ivImage.load(top.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(R.drawable.ic_image)
                }
                tvTitle.text = top.title
                tvScore.text = top.score.toString()

                root.setOnClickListener {
                    onItemClickCallBack?.onItemClick(top)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(ItemTopListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        val value: Int
        if (list.size > max_item) {
            value = max_item
        } else {
            value = list.size
        }
        return value
    }

    interface OnItemClickCallBack{
        fun onItemClick(top: Top)
    }

    fun setData(list: List<Top>?) {
        if (list != null) {
            this.list = list
        }
        notifyDataSetChanged()
    }

}

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.paging.PagingDataAdapter
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.tafh.animemovieapp.R
//import com.tafh.animemovieapp.data.remote.response.attribute.top.Top
//import com.tafh.animemovieapp.databinding.ItemAnimeTopBinding

//class TopAnimeAdapter : PagingDataAdapter<Top, TopAnimeAdapter.TopViewHolder>(DiffUtilCallback) {
//
//    companion object {
//        private val DiffUtilCallback = object : DiffUtil.ItemCallback<Top>() {
//            override fun areItemsTheSame(oldItem: Top, newItem: Top): Boolean =
//                    oldItem.malId == newItem.malId
//
//            override fun areContentsTheSame(oldItem: Top, newItem: Top): Boolean =
//                    oldItem == newItem
//
//        }
//    }
//
//    inner class TopViewHolder(private val binding: ItemAnimeTopBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(top: Top) {
//            binding.apply {
//                ivImage.load(top.imageUrl) {
//                    crossfade(true)
//                    crossfade(1000)
//                    placeholder(R.drawable.ic_image)
//                }
//                tvTitle.text = top.title
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
//        val view = ItemAnimeTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return TopViewHolder(view)
//    }
//
//
//    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
//        val currentItem = getItem(position)
//        if (currentItem != null) {
//            holder.bind(currentItem)
//        }
//
//    }
//
//
//}