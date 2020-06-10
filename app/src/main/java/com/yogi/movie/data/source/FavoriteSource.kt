package com.yogi.movie.data.source

import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface FavoriteSource {

    suspend fun getAllFavorite(): ResultState<List<MovieMdl>>

}