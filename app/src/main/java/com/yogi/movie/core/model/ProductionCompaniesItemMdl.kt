package com.yogi.movie.core.model

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesItemMdl(
    @SerializedName("logo_path")
    val logoPath: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("origin_country")
    val originCountry: String = ""
)