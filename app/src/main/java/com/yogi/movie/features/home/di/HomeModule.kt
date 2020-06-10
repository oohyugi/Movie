package com.yogi.movie.features.home.di

import com.yogi.movie.data.repository.HomeRepo
import com.yogi.movie.data.repository.HomeRepoImpl
import com.yogi.movie.data.source.remote.HomeRemoteDataSource
import com.yogi.movie.features.home.ui.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

val homeModule = module {

    fun provideHomeRepo(homeSource: HomeRemoteDataSource): HomeRepoImpl {
        return HomeRepoImpl(homeSource)
    }

    factory<HomeRepo> {
        provideHomeRepo(get())
    }

    viewModel {
        HomeViewModel(homeRepo = get())
    }
}