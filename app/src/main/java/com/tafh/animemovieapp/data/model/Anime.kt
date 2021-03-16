package com.tafh.animemovieapp.data.model

import com.google.gson.annotations.SerializedName

data class Anime(
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("mal_id")
        val malId: Int,
        val score: Double,
        val title: String
)