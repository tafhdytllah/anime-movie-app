package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tafh.animemovieapp.adapters.GenreListAdapter
import com.tafh.animemovieapp.adapters.SundayAdapter
import com.tafh.animemovieapp.adapters.TopAdapter
import com.tafh.animemovieapp.data.model.GenreAnime
import com.tafh.animemovieapp.data.model.top.Top
import com.tafh.animemovieapp.databinding.FragmentBerandaBinding
import com.tafh.animemovieapp.viewmodels.BerandaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BerandaFragment : Fragment(), TopAdapter.onItemClickListener {

    private lateinit var binding: FragmentBerandaBinding

    private val viewModel: BerandaViewModel by lazy {
        ViewModelProvider(this).get(BerandaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater)
        context ?: return binding.root

        setupAdapter()

        binding.tvTopLihatSemua.setOnClickListener{
            findNavController().navigate(BerandaFragmentDirections.actionBerandaToTopListFragment())
        }

        return binding.root
    }

    private fun setupAdapter() {
        val topAdapter = TopAdapter(this)
        binding.rvTop.adapter = topAdapter
        subscribeTop(topAdapter)

        val genreAdapter = GenreListAdapter()
        binding.rvGenre.adapter = genreAdapter
        setUiGenre(genreAdapter)

        val sundayAdapter = SundayAdapter()
        binding.rvMinggu.adapter = sundayAdapter
//        subscribeSundayList(sundayAdapter)

    }

//    private fun subscribeSundayList(adapter: SundayAdapter) {
//        viewModel.scheduleSunday.observe(viewLifecycleOwner) {
//            if (it.isSuccessful) {
//                val response = it.body()?.sunday
//                adapter.setData(response)
//            } else {
//                Log.d("LOG", "${it.errorBody()}")
//            }
//        }
//    }

    private fun setUiGenre(adapter: GenreListAdapter) {
        val genreList = setDataGenre()
        adapter.setData(genreList)
    }

    private fun subscribeTop(adapter: TopAdapter) {
        viewModel.topList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.top
                adapter.submitList(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
    }


    override fun onItemClick(top: Top) {
        val action = BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(top.malId)
        findNavController().navigate(action)
    }


    private fun setDataGenre() : List<GenreAnime>{
        val list = mutableListOf<GenreAnime>()

        list.add(GenreAnime(1, "Action"))
        list.add(GenreAnime(2, "Adventure"))
        list.add(GenreAnime(4, "Comedy"))
        list.add(GenreAnime(6, "Demons"))
        list.add(GenreAnime(7, "Mystery"))

        list.add(GenreAnime(8, "Drama"))
        list.add(GenreAnime(9, "Ecchi"))
        list.add(GenreAnime(10, "Fantasy"))
        list.add(GenreAnime(11, "Game"))
        list.add(GenreAnime(13, "Historical"))

        list.add(GenreAnime(14, "Horror"))
        list.add(GenreAnime(16, "Magic"))
        list.add(GenreAnime(17, "Martial Arts"))
        list.add(GenreAnime(18, "Mecha"))
        list.add(GenreAnime(19, "Music"))

        list.add(GenreAnime(20, "Parody"))
        list.add(GenreAnime(21, "Samurai"))
        list.add(GenreAnime(22, "Romance"))
        list.add(GenreAnime(23, "School"))
        list.add(GenreAnime(24, "Sci-Fi"))

        list.add(GenreAnime(25, "Shoujo"))
        list.add(GenreAnime(26, "Shoujo Ai"))
        list.add(GenreAnime(27, "Shounen"))
        list.add(GenreAnime(29, "Space"))
        list.add(GenreAnime(30, "Sports"))

        list.add(GenreAnime(31, "Super Power"))
        list.add(GenreAnime(32, "Vampire"))
        list.add(GenreAnime(35, "Harem"))
        list.add(GenreAnime(36, "Slice ofLife"))
        list.add(GenreAnime(37, "Supernatural"))

        list.add(GenreAnime(38, "Military"))
        list.add(GenreAnime(39, "Police"))
        list.add(GenreAnime(40, "Psychological"))
        list.add(GenreAnime(41, "Thriller"))
        list.add(GenreAnime(42, "Seinen"))

        list.add(GenreAnime(43, "Josei"))


        return list

    }


//    private fun setActionbar() {
//
//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
//        val actionBar = (activity as AppCompatActivity).supportActionBar
//        actionBar?.setDisplayShowTitleEnabled(false)
//    }


    // onItemClickCallBack
//        topAdapter.setOnItemClickCallBack(object : TopListAdapter.OnItemClickCallBack{
//            override fun onItemClick(top: Top) {
////                val top = Top(
////                        top.malId
////                )
//                val action = BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(top.malId)
//                findNavController().navigate(action)
//            }
//
//        })

}