package com.tafh.animemovieapp.data.remote.response.schedule

import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.schedule.Sunday

data class SundayResponse(
        @SerializedName("request_hash") val requestHash: String,
        @SerializedName("request_cached") val requestCached: Boolean,
        @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
        @SerializedName("sunday") val sunday: List<Sunday>
)