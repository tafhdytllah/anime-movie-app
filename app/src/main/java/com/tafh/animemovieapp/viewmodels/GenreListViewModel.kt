package com.tafh.animemovieapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tafh.animemovieapp.data.repository.AnimeRepository

class GenreListViewModel @ViewModelInject constructor(
        private val repository: AnimeRepository
): ViewModel() {

    fun getGenreList(genreId: Int) = repository.getGenreList(genreId).cachedIn(viewModelScope)

}