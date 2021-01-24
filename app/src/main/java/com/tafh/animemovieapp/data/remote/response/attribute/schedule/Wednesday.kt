package com.tafh.animemovieapp.data.remote.response.attribute.schedule


import com.google.gson.annotations.SerializedName

data class Wednesday(
    @SerializedName("airing_start")
    val airingStart: String,
    val episodes: Int,
    val genres: List<Genre>,
    @SerializedName("image_url")
    val imageUrl: String,
    val kids: Boolean,
    val licensors: List<String>,
    @SerializedName("mal_id")
    val malId: Int,
    val members: Int,
    val producers: List<Producer>,
    val r18: Boolean,
    val score: Double,
    val source: String,
    val synopsis: String,
    val title: String,
    val type: String,
    val url: String
)