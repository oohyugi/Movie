package com.yogi.movie.data.source.local

import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.source.FavoriteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class FavoriteLocalDataSource(private val favoriteDao: MovieDao) : FavoriteSource {
    override suspend fun getAllFavorite(): ResultState<List<MovieMdl>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val resultdb = favoriteDao.getAllFavorite()
                ResultState.Success(resultdb)
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage)
            }
        }


}