package com.yogi.movie.core.model

import com.google.gson.annotations.SerializedName

data class BaseMdl<T>(
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("results")
    var results: T? = null,
    @SerializedName("total_results")
    var totalResults: Int = 0
)