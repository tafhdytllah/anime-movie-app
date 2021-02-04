package com.tafh.animemovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.tafh.animemovieapp.api.ApiService
import javax.inject.Inject

class AnimeRepository @Inject constructor(
        private val apiService: ApiService
) {
    fun getTopAnime() =
            Pager(
                    config = PagingConfig(
                            pageSize = 10,
                            maxSize = 50,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = {
                        AnimePagingSource(apiService)
                    }
            ).liveData

    suspend fun getTopList() = apiService.getTopList(1)
    suspend fun getDetail(id: Int) = apiService.getDetail(id)

    suspend fun getScheduleSunday() = apiService.getScheduleSunday()
    suspend fun getScheduleMonday() = apiService.getScheduleMonday()
    suspend fun getScheduleTuesday() = apiService.getScheduleTuesday()
    suspend fun getScheduleWednesday() = apiService.getScheduleWednesday()
    suspend fun getScheduleThursday() = apiService.getScheduleThursday()
    suspend fun getScheduleFriday() = apiService.getScheduleFriday()
    suspend fun getScheduleSaturday() = apiService.getScheduleSaturday()


}