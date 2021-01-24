package com.tafh.animemovieapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.remote.response.attribute.schedule.*

data class ScheduleResponse(
    @SerializedName("request_hash") val requestHash: String,
    @SerializedName("request_cached") val requestCached: Boolean,
    @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
    @SerializedName("monday") val monday: List<Monday>,
    @SerializedName("tuesday") val tuesday: List<Tuesday>,
    @SerializedName("wednesday") val wednesday: List<Wednesday>,
    @SerializedName("thursday") val thursday: List<Thursday>,
    @SerializedName("friday") val friday: List<Friday>,
    @SerializedName("saturday") val saturday: List<Saturday>,
    @SerializedName("sunday") val sunday: List<Sunday>
)