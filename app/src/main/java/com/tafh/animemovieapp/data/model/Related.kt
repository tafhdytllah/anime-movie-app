package com.tafh.animemovieapp.data.model

import com.google.gson.annotations.SerializedName

data class Related(
    @SerializedName("Adaptation")
    val adaptation: List<Adaptation>,
    @SerializedName("Side story")
    val sideStory: List<SideStory>,
    @SerializedName("Summary")
    val summary: List<Summary>
)