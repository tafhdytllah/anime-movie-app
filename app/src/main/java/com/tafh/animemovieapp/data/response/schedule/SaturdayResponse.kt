package com.tafh.animemovieapp.data.response.schedule

import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.schedule.*

data class SaturdayResponse(
        @SerializedName("request_hash") val requestHash: String,
        @SerializedName("request_cached") val requestCached: Boolean,
        @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
        @SerializedName("saturday") val saturday: List<Saturday>
)