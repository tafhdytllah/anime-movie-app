package com.tafh.animemovieapp.data.response

import com.tafh.animemovieapp.data.model.Anime

data class SearchResponse(
    val results: List<Anime>
)