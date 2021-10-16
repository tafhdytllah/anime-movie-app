package com.tafh.animemovieapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.tafh.animemovieapp.api.ApiService
import com.tafh.animemovieapp.data.model.Anime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getSearchResults(query: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchPagingSource(apiService = apiService, query = query)
        }
    ).liveData

    fun getGenreList(genreId: Int) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            AnimeGenrePagingSource(apiService = apiService, genreId = genreId)
        }
    ).liveData

    fun getTopList() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimePagingSource(apiService) }
        ).liveData


    suspend fun getTop() = apiService.getTopList(1)

    suspend fun getDetail(id: Int) = apiService.getDetail(id)

    suspend fun getSchedule() = apiService.getSchedule()

    suspend fun getScheduleSunday() = apiService.getScheduleSunday()

    suspend fun getScheduleMonday() = apiService.getScheduleMonday()

    suspend fun getAnimeTuesday() = apiService.getScheduleTuesday()

    suspend fun getScheduleWednesday() = apiService.getScheduleWednesday()

    suspend fun getScheduleThursday() = apiService.getScheduleThursday()

    suspend fun getScheduleFriday() = apiService.getScheduleFriday()

    suspend fun getScheduleSaturday() = apiService.getScheduleSaturday()


}