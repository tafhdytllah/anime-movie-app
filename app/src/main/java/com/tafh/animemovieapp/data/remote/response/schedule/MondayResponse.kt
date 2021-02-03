package com.tafh.animemovieapp.data.remote.response.schedule

import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.schedule.Monday
import com.tafh.animemovieapp.data.model.schedule.Sunday
import com.tafh.animemovieapp.data.model.schedule.Tuesday

data class MondayResponse(
        @SerializedName("request_hash") val requestHash: String,
        @SerializedName("request_cached") val requestCached: Boolean,
        @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
        @SerializedName("monday") val monday: List<Monday>
)