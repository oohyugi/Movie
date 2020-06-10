package com.yogi.movie.data.source.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

object ApiClient {

    fun retrofitClient(
        url: String
    ): Retrofit {

        val okhttpBuilder = OkHttpClient.Builder()
        okhttpBuilder.addInterceptor(createLoggingInterceptor())
        okhttpBuilder.pingInterval(30, TimeUnit.SECONDS)
        okhttpBuilder.readTimeout(1, TimeUnit.MINUTES)
        okhttpBuilder.connectTimeout(1, TimeUnit.MINUTES)

        val okHttpClient = okhttpBuilder.build()

        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}
