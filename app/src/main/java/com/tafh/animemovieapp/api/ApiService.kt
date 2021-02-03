package com.tafh.animemovieapp.api

import com.tafh.animemovieapp.data.model.schedule.Friday
import com.tafh.animemovieapp.data.remote.response.TopResponse
import com.tafh.animemovieapp.data.remote.response.schedule.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    /**
     *  base url = https://api.jikan.moe/v3
     * https://api.jikan.moe/v3/top/anime/1/airing
     * https://api.jikan.moe/v3/schedule
     *  endpoint :
     *      getTopAnime = /top/anime/1/airing
     *      getAllSchedule = /schedule
     */

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v3/"
    }


    @GET("top/anime/{page}/airing")
    suspend fun getTopList(
            @Path("page") page: Int?
    ) : Response<TopResponse>

    @GET("schedule")
    suspend fun getAllSchedule() : Response<ScheduleResponse>

    @GET("schedule/sunday")
    suspend fun getScheduleSunday() : Response<SundayResponse>

    @GET("schedule/monday")
    suspend fun getScheduleMonday() : Response<MondayResponse>
    @GET("schedule/tuesday")
    suspend fun getScheduleTuesday() : Response<TuesdayResponse>
    @GET("schedule/wednesday")
    suspend fun getScheduleWednesday() : Response<WednesdayResponse>
    @GET("schedule/thursday")
    suspend fun getScheduleThursday() : Response<ThursdayResponse>
    @GET("schedule/friday")
    suspend fun getScheduleFriday() : Response<FridayResponse>
    @GET("schedule/saturday")
    suspend fun getScheduleSaturday() : Response<SaturdayResponse>



















}