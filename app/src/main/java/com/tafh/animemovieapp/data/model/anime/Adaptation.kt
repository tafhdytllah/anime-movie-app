package com.tafh.animemovieapp.data.model.anime


import com.google.gson.annotations.SerializedName

data class Adaptation(
    @SerializedName("mal_id")
    val malId: Int,
    val name: String,
    val type: String,
    val url: String
)