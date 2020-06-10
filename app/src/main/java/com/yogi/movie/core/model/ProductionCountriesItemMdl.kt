package com.yogi.movie.core.model

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItemMdl(
    @SerializedName("iso_3166_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = ""
)