package com.tafh.animemovieapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tafh.animemovieapp.data.repository.AnimeRepository
import com.tafh.animemovieapp.data.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel @ViewModelInject constructor(
        private val repository: AnimeRepository
) : ViewModel() {

    fun getDetail(id: Int) = liveData(Dispatchers.IO) {
        emit(repository.getDetail(id))
    }

}