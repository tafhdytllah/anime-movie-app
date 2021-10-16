package com.tafh.animemovieapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.adapters.GenreAdapter
import com.tafh.animemovieapp.adapters.ScheduleAdapter
import com.tafh.animemovieapp.adapters.TopAdapter
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.data.model.GenreList
import com.tafh.animemovieapp.databinding.FragmentBerandaBinding
import com.tafh.animemovieapp.viewmodels.BerandaViewModel
import dagger.hilt.android.AndroidEntryPoint
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
    lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater)
        context ?: return binding.root

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(BerandaFragmentDirections.actionBerandaToSearchFragment())
        }

        setupTopAdapter()
        setupGenreAdapter()
        setupScheduleAdapter()

        binding.tvTopLihatSemua.setOnClickListener {
            findNavController().navigate(BerandaFragmentDirections.actionBerandaToTopListFragment())
        }

        binding.tvScheduleLihatSemua.setOnClickListener {
            findNavController().navigate(BerandaFragmentDirections.actionBerandaToScheduleListFragment())
        }

        return binding.root
    }

    private fun setupTopAdapter() {
        topAdapter = TopAdapter()
        binding.rvTop.adapter = topAdapter
        subscribeTop()
        topAdapter.setOnItemClickCallBack(object : TopAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(anime.malId)
                findNavController().navigate(action)
            }
        })
    }

    private fun setupScheduleAdapter() {
        scheduleAdapter = ScheduleAdapter()
        with(binding.rvSchedule) {
            adapter = scheduleAdapter
        }
        subscribeSchedule(scheduleAdapter)

        scheduleAdapter.setOnItemClickListener(object : ScheduleAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(anime.malId)
                findNavController().navigate(action)
            }
        })
    }

    private fun setupGenreAdapter() {
        genreAdapter = GenreAdapter()
        binding.rvGenre.adapter = genreAdapter
        val genreList = setDataGenre()
        genreAdapter.setData(genreList)

        genreAdapter.setOnItemClickCallback(object : GenreAdapter.onItemClickCallback {
            override fun onItemClick(genreList: GenreList) {
                val action = BerandaFragmentDirections.actionBerandaToGenreListFragment(
                    genreList.id,
                    genreList.name
                )
                findNavController().navigate(action)
            }
        })
    }

    private fun subscribeSchedule(adapter: ScheduleAdapter) {

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


}