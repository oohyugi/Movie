package com.yogi.movie.core.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItemMdl(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("iso_639_1")
    val iso: String = ""
)