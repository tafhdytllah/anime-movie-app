package com.tafh.animemovieapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tafh.animemovieapp.data.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers

class DetailViewModel @ViewModelInject constructor(
        private val repository: AnimeRepository
) : ViewModel() {

    fun getDetail(id: Int) = liveData(Dispatchers.IO) {
        emit(repository.getDetail(id))
    }

}