package com.tafh.animemovieapp.viewmodels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.data.repository.AnimeRepository

class SearchViewModel @ViewModelInject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData<String>()

    val animes = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchAnime(query: String) {
        currentQuery.value = query
    }

}