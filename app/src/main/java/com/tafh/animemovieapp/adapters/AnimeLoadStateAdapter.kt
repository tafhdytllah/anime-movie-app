package com.tafh.animemovieapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tafh.animemovieapp.databinding.LoadStateBinding

class AnimeLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<AnimeLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: LoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {

            if (loadState.equals(LoadState.Loading)) {
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    btnRetry.isVisible = loadState !is LoadState.Loading
                    tvError.isVisible = loadState !is LoadState.Loading
                }
            } else {
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    btnRetry.isVisible = loadState is LoadState.Loading
                    tvError.isVisible = loadState is LoadState.Loading
                }
            }

        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}