package com.yogi.movie.data.repository

import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.source.remote.HomeRemoteDataSource

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface HomeRepo {


    suspend fun getHome(category: String, page: Int): ResultState<BaseMdl<List<MovieMdl>>>


}

class HomeRepoImpl(private val remoteDataSource: HomeRemoteDataSource) : HomeRepo {


    override suspend fun getHome(
        category: String,
        page: Int
    ): ResultState<BaseMdl<List<MovieMdl>>> {
        return remoteDataSource.getHome(category, page)
    }

}