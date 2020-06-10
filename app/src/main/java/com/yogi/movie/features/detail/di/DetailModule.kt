package com.yogi.movie.features.detail.di

import com.yogi.movie.data.repository.DetailRepo
import com.yogi.movie.data.repository.DetailRepoImpl
import com.yogi.movie.data.source.local.DetailLocalDataSource
import com.yogi.movie.data.source.remote.DetailRemoteDataSource
import com.yogi.movie.features.detail.ui.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

val detailModule = module {

    fun provideDetailRepo(
        localDataSource: DetailLocalDataSource,
        remoteDataSource: DetailRemoteDataSource
    ): DetailRepoImpl {
        return DetailRepoImpl(localDataSource, remoteDataSource)
    }

    factory<DetailRepo> {
        provideDetailRepo(get(), get())
    }

    viewModel {
        DetailViewModel(get())
    }
}