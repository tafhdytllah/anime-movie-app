package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tafh.animemovieapp.R
import androidx.paging.LoadState
import com.tafh.animemovieapp.adapters.AnimeLoadStateAdapter
import com.tafh.animemovieapp.adapters.TopListAdapter
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.FragmentTopListBinding
import com.tafh.animemovieapp.viewmodels.TopListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopListFragment : Fragment(R.layout.fragment_top_list), TopListAdapter.onItemClickListener {

    private var _binding: FragmentTopListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TopListViewModel by lazy {
        ViewModelProvider(this).get(TopListViewModel::class.java)
    }

    lateinit var topAdapter: TopListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopListBinding.bind(view)

        setupList()

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

    }

    private fun setupList() {
        topAdapter = TopListAdapter(this)

        val gridLayoutManager = GridLayoutManager(context, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (topAdapter.getItemViewType(position) == topAdapter.LOADING_ITEM) 1 else 3                                   // network_view_type will occupy all 3 span
            }

        }

        binding.apply {

            with(rvTopList) {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                adapter = topAdapter.withLoadStateHeaderAndFooter(
                    header = AnimeLoadStateAdapter { topAdapter.retry() },
                    footer = AnimeLoadStateAdapter { topAdapter.retry() }
                )
            }

            btnTryAgain.setOnClickListener {
                topAdapter.retry()
            }
        }


        topAdapter.addLoadStateListener { loadState ->

            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading

                rvTopList.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
                tvFailed.isVisible = loadState.source.refresh is LoadState.Error
            }

        }

        subscribeTopList(topAdapter)
    }

    private fun subscribeTopList(adapter: TopListAdapter) {
        viewModel.getTopList.observe(viewLifecycleOwner) {

            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onItemClick(anime: Anime) {
        val direction = TopListFragmentDirections.actionTopListFragmentToDetail(anime.malId)
        findNavController().navigate(direction)
    }

}