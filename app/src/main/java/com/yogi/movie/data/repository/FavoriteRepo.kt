package com.yogi.movie.data.repository

import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.source.local.FavoriteLocalDataSource

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface FavoriteRepo {


    suspend fun getAllFavorite(): ResultState<List<MovieMdl>>


}

class FavoriteRepoImpl(private val localDataSource: FavoriteLocalDataSource) : FavoriteRepo {


    override suspend fun getAllFavorite(): ResultState<List<MovieMdl>> {
        return localDataSource.getAllFavorite()
    }


}