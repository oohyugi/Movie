package com.yogi.movie.data.source

import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface HomeSource {

    suspend fun getHome(category: String, page: Int): ResultState<BaseMdl<List<MovieMdl>>>
}