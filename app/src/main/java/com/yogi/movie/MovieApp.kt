package com.yogi.movie

import android.app.Application
import com.yogi.movie.core.di.coreModule
import com.yogi.movie.data.di.dataModule
import com.yogi.movie.features.detail.di.detailModule
import com.yogi.movie.features.favorite.di.favoriteModule
import com.yogi.movie.features.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApp : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MovieApp)
            androidFileProperties()
            modules(listOf(coreModule, dataModule, homeModule, favoriteModule, detailModule))
        }
    }
}