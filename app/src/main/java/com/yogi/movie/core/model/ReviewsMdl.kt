package com.yogi.movie.core.model

import com.google.gson.annotations.SerializedName

data class ReviewsMdl(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("url")
    val url: String = ""
)