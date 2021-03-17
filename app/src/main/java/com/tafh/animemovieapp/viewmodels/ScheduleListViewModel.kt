package com.tafh.animemovieapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tafh.animemovieapp.data.repository.AnimeRepository
import com.tafh.animemovieapp.data.response.TopResponse
import com.tafh.animemovieapp.data.response.day.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Response


class ScheduleListViewModel @ViewModelInject constructor(
        private val repository: AnimeRepository
) : ViewModel() {

    val sundayList: MutableLiveData<Response<SundayResponse>> = MutableLiveData()
    val mondayList: MutableLiveData<Response<MondayResponse>> = MutableLiveData()
    val tuesdayList: MutableLiveData<Response<TuesdayResponse>> = MutableLiveData()
    val wednesdayList: MutableLiveData<Response<WednesdayResponse>> = MutableLiveData()
    val thursdayList: MutableLiveData<Response<ThursdayResponse>> = MutableLiveData()
    val fridayList: MutableLiveData<Response<FridayResponse>> = MutableLiveData()
    val saturdayList: MutableLiveData<Response<SaturdayResponse>> = MutableLiveData()

    init {
        getSundayList()
        getMondayList()
        getTuesdayList()
        getWednesdayist()
        getThursdayList()
        getFridayList()
        getSaturdayList()
    }

    private fun getSundayList() = viewModelScope.launch {
        val response = repository.getScheduleSunday()
        sundayList.value = response
    }

    private fun getMondayList() = viewModelScope.launch {
        val response = repository.getScheduleMonday()
        mondayList.value = response
    }

    private fun getTuesdayList() = viewModelScope.launch {
        val response = repository.getAnimeTuesday()
        tuesdayList.value = response
    }

    private fun getWednesdayist() = viewModelScope.launch {
        val response = repository.getScheduleWednesday()
        wednesdayList.value = response
    }

    private fun getThursdayList() = viewModelScope.launch {
        val response = repository.getScheduleThursday()
        thursdayList.value = response
    }

    private fun getFridayList() = viewModelScope.launch {
        val response = repository.getScheduleFriday()
        fridayList.value = response
    }

    private fun getSaturdayList() = viewModelScope.launch {
        val response = repository.getScheduleSaturday()
        saturdayList.value = response
    }


}