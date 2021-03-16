package com.tafh.animemovieapp.data.response


import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.Aired
import com.tafh.animemovieapp.data.model.Genre
import com.tafh.animemovieapp.data.model.Studio

data class AnimeResponse(
        val malId: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        val aired: Aired,
        val title: String,
        val status: String,
        val score: Double,
        val duration: String,
        val studios: List<Studio>,
        val genres: List<Genre>,
        val synopsis: String
)