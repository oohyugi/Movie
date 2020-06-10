package com.yogi.movie.data.di

import androidx.room.Room
import com.yogi.movie.BuildConfig
import com.yogi.movie.data.source.local.AppDatabase
import com.yogi.movie.data.source.local.DetailLocalDataSource
import com.yogi.movie.data.source.local.FavoriteLocalDataSource
import com.yogi.movie.data.source.local.MovieDao
import com.yogi.movie.data.source.remote.ApiClient
import com.yogi.movie.data.source.remote.ApiServices
import com.yogi.movie.data.source.remote.DetailRemoteDataSource
import com.yogi.movie.data.source.remote.HomeRemoteDataSource
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

val dataModule = module {

    fun provideApiService(baseUrl: String): ApiServices {

        return ApiClient.retrofitClient(baseUrl).create(ApiServices::class.java)
    }
    single {
        provideApiService(BuildConfig.API_URL)
    }


    single { Room.databaseBuilder(get(), AppDatabase::class.java, "movie-test-db").build() }

    single { get<AppDatabase>().favorite() }

    fun provideHomeSource(apiServices: ApiServices): HomeRemoteDataSource {
        return HomeRemoteDataSource(apiServices)
    }
    factory {
        provideHomeSource(get())
    }

    fun provideFavoriteSource(favoriteDao: MovieDao): FavoriteLocalDataSource {
        return FavoriteLocalDataSource(favoriteDao)
    }
    factory {
        provideFavoriteSource(get())
    }

    fun provideLocalDetailSource(favoriteDao: MovieDao): DetailLocalDataSource {
        return DetailLocalDataSource(favoriteDao)
    }
    factory {
        provideLocalDetailSource(get())
    }
    fun provideRemoteDetailSource(apiServices: ApiServices): DetailRemoteDataSource {
        return DetailRemoteDataSource(apiServices)
    }
    factory {
        provideRemoteDetailSource(get())
    }


}
