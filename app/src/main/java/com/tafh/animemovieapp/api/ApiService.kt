package com.tafh.animemovieapp.api

import com.tafh.animemovieapp.data.response.*
import com.tafh.animemovieapp.data.response.day.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    /**
     *  base url = https://api.jikan.moe/v3/
     *
     * endpoint :
     *      getTopAnime = top/anime/page/airing
     *      getGenreAnime = genre/type/genre_id/page
     *      getAllSchedule = schedule
     *      getDetail = anime/id
     *      getDayAnime = schedule/day
     *      getSearch = search/anime?q=(query)&page=(page)
     *
     * example :
     *      api.jikan.moe/v3/top/anime/1/airing
     *      api.jikan.moe/v3/genre/anime/2/1
     *      api.jikan.moe/v3/schedule
     *      api.jikan.moe/v3/anime/1
     *      api.jikan.moe/v3/schedule/monday
     *      api.jikan.moe/v3/search/anime?q=onepiece&page=1
     *
     */

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v3/"
    }

    @GET("search/anime")
    suspend fun searchAnime(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<SearchResponse>

    @GET("top/anime/{page}/airing")
    suspend fun getTopList(
        @Path("page") page: Int?
    ): Response<TopResponse>

    @GET("genre/anime/{genre_id}/{page}")
    suspend fun getGenreList(
        @Path("genre_id") genreId: Int?,
        @Path("page") page: Int?
    ): Response<GenreResponse>

    @GET("anime/{id}")
    suspend fun getDetail(
        @Path("id") id: Int?
    ): Response<AnimeResponse>

    @GET("schedule")
    suspend fun getSchedule(): Response<ScheduleResponse>

    @GET("schedule/sunday")
    suspend fun getScheduleSunday(): Response<SundayResponse>

    @GET("schedule/monday")
    suspend fun getScheduleMonday(): Response<MondayResponse>

    @GET("schedule/tuesday")
    suspend fun getScheduleTuesday(): Response<TuesdayResponse>

    @GET("schedule/wednesday")
    suspend fun getScheduleWednesday(): Response<WednesdayResponse>

    @GET("schedule/thursday")
    suspend fun getScheduleThursday(): Response<ThursdayResponse>

    @GET("schedule/friday")
    suspend fun getScheduleFriday(): Response<FridayResponse>

    @GET("schedule/saturday")
    suspend fun getScheduleSaturday(): Response<SaturdayResponse>

}