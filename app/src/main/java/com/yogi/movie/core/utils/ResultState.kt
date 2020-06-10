package com.yogi.movie.core.utils

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

sealed class ResultState<out T : Any?> {
    data class Success<out T : Any?>(val data: T?, val isLast: Boolean = false) : ResultState<T>()
    data class Error(val errorMessage: String?) : ResultState<Nothing>()
}