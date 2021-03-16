package com.tafh.animemovieapp.data.response.day

import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.Anime

data class FridayResponse(
        @SerializedName("request_hash") val requestHash: String,
        @SerializedName("request_cached") val requestCached: Boolean,
        @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
        @SerializedName("friday") val friday: List<Anime>
)