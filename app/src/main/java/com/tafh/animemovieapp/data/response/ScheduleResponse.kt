package com.tafh.animemovieapp.data.response


import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.Anime

data class ScheduleResponse(
        @SerializedName("request_hash") val requestHash: String,
        @SerializedName("request_cached") val requestCached: Boolean,
        @SerializedName("request_cache_expiry") val requestCacheExpiry: Int,
        @SerializedName("monday") val monday: List<Anime>,
        @SerializedName("tuesday") val tuesday: List<Anime>,
        @SerializedName("wednesday") val wednesday: List<Anime>,
        @SerializedName("thursday") val thursday: List<Anime>,
        @SerializedName("friday") val friday: List<Anime>,
        @SerializedName("saturday") val saturday: List<Anime>,
        @SerializedName("sunday") val sunday: List<Anime>
)