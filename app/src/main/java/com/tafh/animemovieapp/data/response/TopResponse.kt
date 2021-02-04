package com.tafh.animemovieapp.data.response


import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.top.Top

data class TopResponse(
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int,
    @SerializedName("request_cached")
    val requestCached: Boolean,
    @SerializedName("request_hash")
    val requestHash: String,
    val top: List<Top>
)