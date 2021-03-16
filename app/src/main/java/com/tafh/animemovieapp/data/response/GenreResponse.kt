package com.tafh.animemovieapp.data.response


import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.Anime
import com.tafh.animemovieapp.data.model.MalUrl

data class GenreResponse(
        @SerializedName("anime")
        val animeGenres: List<Anime>,
        @SerializedName("item_count")
        val itemCount: Int,
        @SerializedName("mal_url")
        val malUrl: MalUrl,
        @SerializedName("request_cache_expiry")
        val requestCacheExpiry: Int,
        @SerializedName("request_cached")
        val requestCached: Boolean,
        @SerializedName("request_hash")
        val requestHash: String
)