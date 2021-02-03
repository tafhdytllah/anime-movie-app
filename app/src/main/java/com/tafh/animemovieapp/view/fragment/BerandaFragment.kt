package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tafh.animemovieapp.data.model.GenreAnime
import com.tafh.animemovieapp.data.model.top.Top
import com.tafh.animemovieapp.databinding.FragmentBerandaBinding
import com.tafh.animemovieapp.adapters.GenreListAdapter
import com.tafh.animemovieapp.adapters.SundayAdapter
import com.tafh.animemovieapp.adapters.TopListAdapter
import com.tafh.animemovieapp.viewmodels.BerandaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BerandaViewModel

    private var genreList = arrayListOf<GenreAnime>()

    private val topAdapter = TopListAdapter()
    private val genreAdapter = GenreListAdapter()
    private val sundayAdapter = SundayAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)

        setActionbar()

        viewModel = ViewModelProvider(this).get(BerandaViewModel::class.java)

        setGenreList()

        setAdapter()


        // Top List
        viewModel.topList.observe(viewLifecycleOwner, Observer {
//            mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            if (it.isSuccessful) {
                val response = it.body()?.top
                topAdapter.setData(response)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        })

        // Genre List
        genreAdapter.setData(genreList)

//         Minggu List
        viewModel.scheduleSunday.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val response = it.body()?.sunday
                sundayAdapter.setData(response)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        })

        // onItemClickCallBack
        topAdapter.setOnItemClickCallBack(object : TopListAdapter.OnItemClickCallBack{
            override fun onItemClick(top: Top) {
//                val top = Top(
//                        top.malId
//                )
                val action = BerandaFragmentDirections.actionBerandaFragmentToDetailFragment()
                findNavController().navigate(action)
            }

        })

        return binding.root
    }

    private fun setActionbar() {

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

    }


    private fun setAdapter() {
        binding.apply {
            // Top List RecyclerView
            rvTopList.setHasFixedSize(true)
            rvTopList.adapter = topAdapter

            // Genre List RecyclerView
            rvGenreList.setHasFixedSize(true)
            rvGenreList.adapter = genreAdapter

            // Minggu List RecyclerView
            rvMingguList.setHasFixedSize(true)
            rvMingguList.adapter = sundayAdapter



        }
    }

    private fun setGenreList() {

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

        genreList.addAll(list)


    }

}