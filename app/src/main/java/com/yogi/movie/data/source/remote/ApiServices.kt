package com.yogi.movie.data.source.remote

import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.DetailMovieMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.model.ReviewsMdl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface ApiServices {

    @GET("{category}")
    suspend fun getHomeApi(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseMdl<List<MovieMdl>>>

    @GET("{movieId}")
    suspend fun getDetailApi(
        @Path("movieId") id: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieMdl>

    @GET("{movieId}/reviews")
    suspend fun getReviewApi(
        @Path("movieId") id: Int,
        @Query("api_key") apiKey: String
    ): Response<BaseMdl<List<ReviewsMdl>>>
}