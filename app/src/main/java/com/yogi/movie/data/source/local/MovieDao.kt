package com.yogi.movie.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.yogi.movie.core.model.MovieMdl

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

@Dao
interface MovieDao {

    @Insert(onConflict = IGNORE)
    fun insertFavorite(item: MovieMdl)


    @Query("DELETE FROM MovieMdl WHERE id =:mid")
    fun deleteFavorite(mid: Int)

    @Query("SELECT * FROM MovieMdl")
    fun getAllFavorite(): List<MovieMdl>

    @Query("SELECT * FROM MovieMdl where id=:mId")
    fun getByIdFavorite(mId: Int): MovieMdl
}