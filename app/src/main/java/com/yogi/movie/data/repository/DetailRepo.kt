package com.yogi.movie.data.repository

import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.DetailMovieMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.model.ReviewsMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.source.local.DetailLocalDataSource
import com.yogi.movie.data.source.remote.DetailRemoteDataSource

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

interface DetailRepo {


    suspend fun getFavoriteById(id: Int): ResultState<MovieMdl>
    suspend fun setFavorite(item: MovieMdl): ResultState<String>
    suspend fun deleteFavorite(id: Int): ResultState<String>
    suspend fun getDetail(movieId: Int): ResultState<DetailMovieMdl>
    suspend fun getReviews(movieId: Int): ResultState<BaseMdl<List<ReviewsMdl>>>

}

class DetailRepoImpl(
    private val localDataSource: DetailLocalDataSource,
    private val remoteDataSource: DetailRemoteDataSource
) : DetailRepo {


    override suspend fun getFavoriteById(id: Int): ResultState<MovieMdl> {
        return localDataSource.getFavoriteById(id)
    }

    override suspend fun setFavorite(item: MovieMdl): ResultState<String> {
        return localDataSource.setFavorite(item)
    }

    override suspend fun deleteFavorite(id: Int): ResultState<String> {
        return localDataSource.deleteFavorite(id)
    }

    override suspend fun getDetail(movieId: Int): ResultState<DetailMovieMdl> {
        return remoteDataSource.getDetail(movieId)
    }

    override suspend fun getReviews(movieId: Int): ResultState<BaseMdl<List<ReviewsMdl>>> {
        return remoteDataSource.getReviews(movieId)

    }

}