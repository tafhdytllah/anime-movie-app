package com.tafh.animemovieapp.data.model

import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("mal_id")
    val malId: Int,
    val name: String,
    val type: String,
    val url: String
)