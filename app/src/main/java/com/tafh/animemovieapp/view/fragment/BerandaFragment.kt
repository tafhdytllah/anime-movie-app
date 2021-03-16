package com.tafh.animemovieapp.view.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.adapters.GenreAdapter
import com.tafh.animemovieapp.adapters.AnimeDayAdapter
import com.tafh.animemovieapp.adapters.TopAdapter
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.data.model.GenreList
import com.tafh.animemovieapp.data.response.ScheduleResponse
import com.tafh.animemovieapp.databinding.FragmentBerandaBinding
import com.tafh.animemovieapp.viewmodels.BerandaViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class BerandaFragment : Fragment(R.layout.fragment_beranda) {

    private lateinit var binding: FragmentBerandaBinding

    private val viewModel: BerandaViewModel by lazy {
        ViewModelProvider(this).get(BerandaViewModel::class.java)
    }

    lateinit var topAdapter: TopAdapter
    lateinit var genreAdapter: GenreAdapter
    lateinit var animeDayAdapter: AnimeDayAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater)
        context ?: return binding.root

        setupTopAdapter()
        setupGenreAdapter()
        setupAnimeDayAdapter()

        binding.tvTopLihatSemua.setOnClickListener {
            findNavController().navigate(BerandaFragmentDirections.actionBerandaToTopListFragment())
        }

        return binding.root
    }

    private fun setupAnimeDayAdapter() {
        animeDayAdapter = AnimeDayAdapter()
        with(binding.rvEpisodeTerbaru) {
            adapter = animeDayAdapter
        }
        subscribeAnimeDay(animeDayAdapter)
    }

    private fun setupGenreAdapter() {
        genreAdapter = GenreAdapter()
        binding.rvGenre.adapter = genreAdapter
        val genreList = setDataGenre()
        genreAdapter.setData(genreList)

        genreAdapter.setOnItemClickCallback(object : GenreAdapter.OnItemClickCallback {
            override fun OnItemClick(genreList: GenreList) {
                val action = BerandaFragmentDirections.actionBerandaToGenreListFragment(genreList.id, genreList.name)
                findNavController().navigate(action)
            }

        })
    }

    private fun setupTopAdapter() {
        topAdapter = TopAdapter()
        binding.rvTop.adapter = topAdapter
        subscribeTop()
        topAdapter.setOnItemClickCallBack(object : TopAdapter.OnItemClickCallback {
            override fun onItemClick(top: Anime) {
                val action = BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(top.malId)
                findNavController().navigate(action)
            }

        })
    }


    private fun subscribeAnimeDay(adapter: AnimeDayAdapter) {

        val currentDay = getCurrentDay().toLowerCase(Locale.ROOT)

        viewModel.episodeTerbaru.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val animeList: List<Anime>?
                when (currentDay) {
                    "sunday" -> animeList = it.body()?.sunday
                    "monday" -> animeList = it.body()?.monday
                    "tuesday" -> animeList = it.body()?.tuesday
                    "wednesday" -> animeList = it.body()?.wednesday
                    "thursday" -> animeList = it.body()?.thursday
                    "friday" -> animeList = it.body()?.friday
                    else -> animeList = it.body()?.saturday
                }
                adapter.setData(animeList)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDay(): String {
        val day: String

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("EEEE")
            day = current.format(formatter)
            return day

        } else {
            val date = Date()
            val formatter = SimpleDateFormat("EEEE")
            day = formatter.format(date)
            return day
        }


    }


    private fun subscribeTop() {
        viewModel.topList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.top
                topAdapter.submitList(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
    }


//    override fun onItemClick(top: Top) {
//        val action = BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(top.malId)
//        findNavController().navigate(action)
//    }


    private fun setDataGenre(): List<GenreList> {
        val list = mutableListOf<GenreList>()

        list.add(GenreList(1, "Action"))
        list.add(GenreList(2, "Adventure"))
        list.add(GenreList(4, "Comedy"))
        list.add(GenreList(6, "Demons"))
        list.add(GenreList(7, "Mystery"))

        list.add(GenreList(8, "Drama"))
        list.add(GenreList(9, "Ecchi"))
        list.add(GenreList(10, "Fantasy"))
        list.add(GenreList(11, "Game"))
        list.add(GenreList(13, "Historical"))

        list.add(GenreList(14, "Horror"))
        list.add(GenreList(16, "Magic"))
        list.add(GenreList(17, "Martial Arts"))
        list.add(GenreList(18, "Mecha"))
        list.add(GenreList(19, "Music"))

        list.add(GenreList(20, "Parody"))
        list.add(GenreList(21, "Samurai"))
        list.add(GenreList(22, "Romance"))
        list.add(GenreList(23, "School"))
        list.add(GenreList(24, "Sci-Fi"))

        list.add(GenreList(25, "Shoujo"))
        list.add(GenreList(26, "Shoujo Ai"))
        list.add(GenreList(27, "Shounen"))
        list.add(GenreList(29, "Space"))
        list.add(GenreList(30, "Sports"))

        list.add(GenreList(31, "Super Power"))
        list.add(GenreList(32, "Vampire"))
        list.add(GenreList(35, "Harem"))
        list.add(GenreList(36, "Slice ofLife"))
        list.add(GenreList(37, "Supernatural"))

        list.add(GenreList(38, "Military"))
        list.add(GenreList(39, "Police"))
        list.add(GenreList(40, "Psychological"))
        list.add(GenreList(41, "Thriller"))
        list.add(GenreList(42, "Seinen"))

        list.add(GenreList(43, "Josei"))


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