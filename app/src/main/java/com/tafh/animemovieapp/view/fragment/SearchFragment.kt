package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.adapters.AnimeLoadStateAdapter
import com.tafh.animemovieapp.adapters.SearchAdapter
import com.tafh.animemovieapp.adapters.TopListAdapter
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.FragmentSearchBinding
import com.tafh.animemovieapp.databinding.FragmentTopListBinding
import com.tafh.animemovieapp.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search),
    SearchAdapter.onItemClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

    lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view)

        searchAdapter = SearchAdapter(this)

        val gridLayoutManager = GridLayoutManager(context, 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (searchAdapter.getItemViewType(position) == searchAdapter.LOADING_ITEM) 1 else 3                                   // network_view_type will occupy all 3 span
            }
        }

        binding.apply {
            rvSearch.apply {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                // adapter setup
                adapter = searchAdapter.withLoadStateHeaderAndFooter(
                    header = AnimeLoadStateAdapter { searchAdapter.retry() },
                    footer = AnimeLoadStateAdapter { searchAdapter.retry() }
                )

                btnTryAgain.setOnClickListener { searchAdapter.retry() }
            }

            searchAdapter.addLoadStateListener { loadState ->

                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading

                    rvSearch.isVisible = loadState.source.refresh is LoadState.NotLoading
                    btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
                    tvFailed.isVisible = loadState.source.refresh is LoadState.Error
                }
            }

            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }

            swSearchAnime.apply {
                isIconified = false // show keyboard and focus
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        if (query != null && query.length >= 3) {
                            rvSearch.scrollToPosition(0)
                            viewModel.searchAnime(query)

                            viewModel.animes.observe(viewLifecycleOwner) {
                                searchAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            }

                            rvSearch.clearFocus()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "minimal 3 karakter",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        return true


                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }

        }

    }

    override fun onItemClick(anime: Anime) {
        val directions = SearchFragmentDirections.actionSearchFragmentToDetail(anime.malId)
        findNavController().navigate(directions)
    }

}