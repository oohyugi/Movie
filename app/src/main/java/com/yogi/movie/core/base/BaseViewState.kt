package com.yogi.movie.core.base

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

sealed class BaseViewState<out R> {
    object Loading : BaseViewState<Nothing>()
    data class Error(val errorMessage: String?) : BaseViewState<Nothing>()
    data class Success<T>(val data: T?, val isLast: Boolean? = false) : BaseViewState<T>()
}