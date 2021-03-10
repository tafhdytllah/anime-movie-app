package com.tafh.animemovieapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafh.animemovieapp.data.repository.AnimeRepository
import com.tafh.animemovieapp.data.response.TopResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class BerandaViewModel @ViewModelInject constructor(
        private val repository: AnimeRepository
) : ViewModel() {

    val topList: MutableLiveData<Response<TopResponse>> = MutableLiveData()
//    val scheduleSunday: MutableLiveData<Response<SundayResponse>> = MutableLiveData()

    init {
        getTopList()
//        getScheduleSunday()
    }

//    private fun getScheduleSunday() = viewModelScope.launch {
//        val response = repository.getScheduleSunday()
//        scheduleSunday.value = response
//    }

    private fun getTopList() = viewModelScope.launch {
        val response = repository.getTop()
//        topList.value = response
    }


}