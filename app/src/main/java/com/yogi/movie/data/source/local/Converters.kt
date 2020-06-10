package com.yogi.movie.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yogi.movie.core.model.MovieMdl

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return if (value != null) {
            Gson().fromJson(value, listType)
        } else {
            null
        }


    }

    @TypeConverter
    fun fromStringFavorite(value: String?): List<MovieMdl>? {
        val listType = object : TypeToken<List<MovieMdl>>() {

        }.type
        return if (value != null) {
            Gson().fromJson(value, listType)
        } else {
            null
        }


    }

    @TypeConverter
    fun fromFavorite(value: List<MovieMdl>?): String? {
        val gson = Gson()
        return if (value != null) {
            gson.toJson(value)
        } else {
            null
        }


    }


}