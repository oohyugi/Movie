package com.yogi.movie.core.utils

import java.net.ConnectException

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

suspend fun <T : Any> fetchState(call: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        call.invoke()
    } catch (e: ConnectException) {
        ResultState.Error(e.message)
    } catch (e: Exception) {

        ResultState.Error(e.message)
    } catch (e: Throwable) {
        ResultState.Error(e.message)

    }
}