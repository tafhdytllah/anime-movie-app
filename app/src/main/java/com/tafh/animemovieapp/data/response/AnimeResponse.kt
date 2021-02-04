package com.tafh.animemovieapp.data.response


import com.google.gson.annotations.SerializedName
import com.tafh.animemovieapp.data.model.anime.*

data class AnimeResponse(
        val aired: Aired,
        val airing: Boolean,
        val background: Any,
        val broadcast: String,
        val duration: String,
        @SerializedName("ending_themes")
    val endingThemes: List<String>,
        val episodes: Int,
        val favorites: Int,
        val genres: List<Genre>,
        @SerializedName("image_url")
    val imageUrl: String,
        val licensors: List<Licensor>,
        @SerializedName("mal_id")
    val malId: Int,
        val members: Int,
        @SerializedName("opening_themes")
    val openingThemes: List<String>,
        val popularity: Int,
        val premiered: String,
        val producers: List<Producer>,
        val rank: Int,
        val rating: String,
        val related: Related,
        @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int,
        @SerializedName("request_cached")
    val requestCached: Boolean,
        @SerializedName("request_hash")
    val requestHash: String,
        val score: Double,
        @SerializedName("scored_by")
    val scoredBy: Int,
        val source: String,
        val status: String,
        val studios: List<Studio>,
        val synopsis: String,
        val title: String,
        @SerializedName("title_english")
    val titleEnglish: String,
        @SerializedName("title_japanese")
    val titleJapanese: String,
        @SerializedName("title_synonyms")
    val titleSynonyms: List<Any>,
        @SerializedName("trailer_url")
    val trailerUrl: String,
        val type: String,
        val url: String
)