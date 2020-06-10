package com.yogi.movie.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yogi.movie.core.model.MovieMdl

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class PrefHelper(private val context: Context) {

    companion object {
        const val PREF_FAVORITE = "pref_favorite"
    }


    private fun pref(): SharedPreferences {
        return context.getSharedPreferences("movie", Context.MODE_PRIVATE)
    }

    fun saveFavorite(data: MovieMdl?) {

        val listHistory: MutableList<MovieMdl> = mutableListOf()
        getFavorite()?.let { listHistory.addAll(it) }
        data?.let {
            listHistory.add(data)
        }

        pref().edit().putString(PREF_FAVORITE, Gson().toJson(listHistory)).apply()
    }

    fun getFavorite(): MutableList<MovieMdl>? {

        val typeToken = object : TypeToken<MutableList<MovieMdl>>() {}.type
        val jsonString = pref().getString(
            PREF_FAVORITE, null
        )
        var listHistory: MutableList<MovieMdl>? = mutableListOf()
        jsonString?.apply {
            listHistory = Gson().fromJson<MutableList<MovieMdl>>(
                jsonString, typeToken
            )
        }
        return listHistory


    }


}