package com.yogi.movie.data.source.remote

import com.yogi.movie.BuildConfig
import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.DetailMovieMdl
import com.yogi.movie.core.model.ReviewsMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.core.utils.fetchState
import com.yogi.movie.data.source.DetailSource

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class DetailRemoteDataSource(private val apiServices: ApiServices) : DetailSource {

    override suspend fun getDetail(movieId: Int): ResultState<DetailMovieMdl> {
        return fetchState {
            val response = apiServices.getDetailApi(movieId, apiKey = BuildConfig.API_KEY)
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {

                ResultState.Error(response.message())
            }
        }
    }

    override suspend fun getReviews(movieId: Int): ResultState<BaseMdl<List<ReviewsMdl>>> {
        return fetchState {
            val response = apiServices.getReviewApi(movieId, apiKey = BuildConfig.API_KEY)
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {

                ResultState.Error(response.message())
            }
        }
    }


}