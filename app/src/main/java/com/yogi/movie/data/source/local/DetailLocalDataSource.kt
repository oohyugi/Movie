package com.yogi.movie.data.source.local

import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.source.DetailSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class DetailLocalDataSource(private val favoriteDao: MovieDao) : DetailSource {


    override suspend fun getFavoriteById(id: Int): ResultState<MovieMdl> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val resultdb = favoriteDao.getByIdFavorite(id)
                ResultState.Success(resultdb)
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage)
            }
        }

    override suspend fun setFavorite(item: MovieMdl): ResultState<String> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                favoriteDao.insertFavorite(item)
                ResultState.Success("Success")
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage)
            }
        }

    override suspend fun deleteFavorite(id: Int): ResultState<String> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                favoriteDao.deleteFavorite(id)
                ResultState.Success("Success")
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage)
            }
        }

}