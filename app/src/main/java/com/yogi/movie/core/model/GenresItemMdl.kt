package com.yogi.movie.core.model

import com.google.gson.annotations.SerializedName

data class GenresItemMdl(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0
)