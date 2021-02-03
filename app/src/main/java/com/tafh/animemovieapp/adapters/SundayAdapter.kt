package com.tafh.animemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.schedule.Sunday
import com.tafh.animemovieapp.databinding.ItemSundayListBinding

class SundayAdapter : RecyclerView.Adapter<SundayAdapter.SundayViewHolder>() {

    private var list = emptyList<Sunday>()

    inner class SundayViewHolder(private val binding: ItemSundayListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sunday: Sunday) {
            binding.apply {
                ivImage.load(sunday.imageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(R.drawable.ic_image)
                }
                tvTitle.text = sunday.title
                tvScore.text = sunday.score.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SundayViewHolder {
        return SundayViewHolder(ItemSundayListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SundayViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(list: List<Sunday>?) {
        if (list != null) {
            this.list = list
        }
        notifyDataSetChanged()
    }
}