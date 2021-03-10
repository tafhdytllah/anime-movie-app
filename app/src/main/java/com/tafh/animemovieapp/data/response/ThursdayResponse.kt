package com.tafh.animemovieapp.data.response

import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.schedule.Monday
import com.tafh.animemovieapp.data.model.schedule.Sunday
import com.tafh.animemovieapp.data.model.schedule.Thursday
import com.tafh.animemovieapp.data.model.schedule.Tuesday

data class ThursdayResponse(
        @SerializedName("request_hash") val requestHash: String,
        @SerializedName("request_cached") val requestCached: Boolean,
        @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
        @SerializedName("thursday") val thursday: List<Thursday>
)