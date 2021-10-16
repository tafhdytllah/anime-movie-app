package com.tafh.animemovieapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tafh.animemovieapp.data.repository.AnimeRepository


class TopListViewModel @ViewModelInject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    val getTopList = repository.getTopList().cachedIn(viewModelScope)

}