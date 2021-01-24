package com.tafh.animemovieapp.data.remote.response.attribute.schedule


import com.google.gson.annotations.SerializedName

data class Producer(
    @SerializedName("mal_id")
    val malId: Int,
    val name: String,
    val type: String,
    val url: String
)