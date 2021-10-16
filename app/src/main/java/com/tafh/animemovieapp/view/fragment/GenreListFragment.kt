package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.adapters.AnimeLoadStateAdapter
import com.tafh.animemovieapp.adapters.GenreListAdapter
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.FragmentGenreListBinding
import com.tafh.animemovieapp.viewmodels.GenreListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreListFragment : Fragment(R.layout.fragment_genre_list),
    GenreListAdapter.onItemClickListener {

    private val args by navArgs<GenreListFragmentArgs>()

    private var _binding: FragmentGenreListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenreListViewModel by lazy {
        ViewModelProvider(this).get(GenreListViewModel::class.java)
    }

    lateinit var genreAdapter: GenreListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGenreListBinding.bind(view)
        binding.tvToolbar.text = args.genreName

        val genreId = args.genreId

        setupGenreList(genreId)

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun setupGenreList(genreId: Int) {
        genreAdapter = GenreListAdapter(this)

        val gridLayoutManager = GridLayoutManager(context, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (genreAdapter.getItemViewType(position) == genreAdapter.LOADING_ITEM) 1 else 3
            }
        }

        binding.apply {
            with(rvGenreList) {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                adapter = genreAdapter.withLoadStateHeaderAndFooter(
                    header = AnimeLoadStateAdapter { genreAdapter.retry() },
                    footer = AnimeLoadStateAdapter { genreAdapter.retry() }
                )
            }

            btnTryAgain.setOnClickListener {
                genreAdapter.retry()
            }
        }

        genreAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading

                rvGenreList.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
                tvFailed.isVisible = loadState.source.refresh is LoadState.Error
            }
        }

        subscribeGenreList(genreAdapter, genreId)
    }

    private fun subscribeGenreList(adapter: GenreListAdapter, genreId: Int) {

        viewModel.getGenreList(genreId).observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onItemClick(anime: Anime) {
        val direction = GenreListFragmentDirections.actionGenreListFragmentToDetail(anime.malId)
        findNavController().navigate(direction)
    }

}