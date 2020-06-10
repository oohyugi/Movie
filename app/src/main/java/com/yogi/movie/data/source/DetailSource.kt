package com.yogi.movie.data.source

import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.DetailMovieMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.model.ReviewsMdl
import com.yogi.movie.core.utils.ResultState

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface DetailSource {

    suspend fun getFavoriteById(id: Int): ResultState<MovieMdl> {
        return ResultState.Success(null)
    }

    suspend fun setFavorite(item: MovieMdl): ResultState<String> {
        return ResultState.Success(null)
    }

    suspend fun deleteFavorite(id: Int): ResultState<String> {
        return ResultState.Success(null)
    }

    suspend fun getDetail(movieId: Int): ResultState<DetailMovieMdl> {
        return ResultState.Success(null)
    }

    suspend fun getReviews(movieId: Int): ResultState<BaseMdl<List<ReviewsMdl>>> {
        return ResultState.Success(null)
    }
}