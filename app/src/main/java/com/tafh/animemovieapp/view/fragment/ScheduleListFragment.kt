package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.adapters.days.*
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.databinding.FragmentScheduleListBinding
import com.tafh.animemovieapp.viewmodels.ScheduleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleListFragment : Fragment(R.layout.fragment_schedule_list) {

    private var _binding: FragmentScheduleListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScheduleListViewModel by lazy {
        ViewModelProvider(this).get(ScheduleListViewModel::class.java)
    }

    lateinit var sundayAdapter: SundayAdapter
    lateinit var mondayAdapter: MondayAdapter
    lateinit var tuesdayAdapter: TuesdayAdapter
    lateinit var wednesdayAdapter: WednesdayAdapter
    lateinit var thursdayAdapter: ThursdayAdapter
    lateinit var fridayAdapter: FridayAdapter
    lateinit var saturdayAdapter: SaturdayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentScheduleListBinding.bind(view)

        setupAdapter()

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun setupAdapter() {
        sundayAdapter = SundayAdapter()
        mondayAdapter = MondayAdapter()
        tuesdayAdapter = TuesdayAdapter()
        wednesdayAdapter = WednesdayAdapter()
        thursdayAdapter = ThursdayAdapter()
        fridayAdapter = FridayAdapter()
        saturdayAdapter = SaturdayAdapter()

        binding.apply {
            with(rvSunday) {
                adapter = sundayAdapter
                setHasFixedSize(true)
            }
            with(rvMonday) {
                adapter = mondayAdapter
                setHasFixedSize(true)
            }
            with(rvTuesday) {
                adapter = tuesdayAdapter
                setHasFixedSize(true)
            }
            with(rvWednesday) {
                adapter = wednesdayAdapter
                setHasFixedSize(true)
            }
            with(rvThursday) {
                adapter = thursdayAdapter
                setHasFixedSize(true)
            }
            with(rvFriday) {
                adapter = fridayAdapter
                setHasFixedSize(true)
            }
            with(rvSaturday) {
                adapter = saturdayAdapter
                setHasFixedSize(true)
            }
        }

        subsribeUI()

        sundayAdapter.setOnItemClickListener(object : SundayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

        mondayAdapter.setOnItemClickListener(object : MondayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

        tuesdayAdapter.setOnItemClickListener(object : TuesdayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

        wednesdayAdapter.setOnItemClickListener(object : WednesdayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

        thursdayAdapter.setOnItemClickListener(object : ThursdayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

        fridayAdapter.setOnItemClickListener(object : FridayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

        saturdayAdapter.setOnItemClickListener(object : SaturdayAdapter.onItemClickCallback {
            override fun onItemClick(anime: Anime) {
                val action =
                    ScheduleListFragmentDirections.actionScheduleListFragmentToDetail(anime.malId)
                findNavController().navigate(action)
            }
        })

    }

    private fun subsribeUI() {
        viewModel.sundayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.sunday
                sundayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }

        viewModel.mondayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.monday
                mondayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
        viewModel.tuesdayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.tuesday
                tuesdayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
        viewModel.wednesdayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.wednesday
                wednesdayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
        viewModel.thursdayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.thursday
                thursdayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
        viewModel.fridayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.friday
                fridayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }
        viewModel.saturdayList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val list = it.body()?.saturday
                saturdayAdapter.setData(list)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        }

    }


}