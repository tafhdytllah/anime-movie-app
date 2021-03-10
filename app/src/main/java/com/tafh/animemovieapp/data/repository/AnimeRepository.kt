package com.tafh.animemovieapp.data.repository

import androidx.paging.*
import com.tafh.animemovieapp.api.ApiService
import javax.inject.Inject

class AnimeRepository @Inject constructor(
        private val apiService: ApiService
) {
    val getTopList =
        Pager(
                config = PagingConfig(
                        pageSize = 10,
                        enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    AnimePagingSource(apiService)
                }
        ).liveData

    suspend fun getTop() = apiService.getTopList(1)

    suspend fun getDetail(id: Int) = apiService.getDetail(id)

//    suspend fun getScheduleSunday() = apiService.getScheduleSunday()
//
//    suspend fun getScheduleMonday() = apiService.getScheduleMonday()
//    suspend fun getScheduleTuesday() = apiService.getScheduleTuesday()
//    suspend fun getScheduleWednesday() = apiService.getScheduleWednesday()
//    suspend fun getScheduleThursday() = apiService.getScheduleThursday()
//    suspend fun getScheduleFriday() = apiService.getScheduleFriday()
//    suspend fun getScheduleSaturday() = apiService.getScheduleSaturday()


}