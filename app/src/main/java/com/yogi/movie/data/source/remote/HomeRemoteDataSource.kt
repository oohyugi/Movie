package com.yogi.movie.data.source.remote

import com.yogi.movie.BuildConfig
import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.core.utils.fetchState
import com.yogi.movie.data.source.HomeSource

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeRemoteDataSource(private val apiServices: ApiServices) : HomeSource {

    override suspend fun getHome(
        category: String,
        page: Int
    ): ResultState<BaseMdl<List<MovieMdl>>> {
        return fetchState {
            val response = apiServices.getHomeApi(
                category = category,
                page = page,
                apiKey = BuildConfig.API_KEY
            )
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {

                ResultState.Error(response.message())
            }
        }
    }

}