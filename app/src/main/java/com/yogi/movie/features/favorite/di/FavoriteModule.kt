package com.yogi.movie.features.favorite.di

import com.yogi.movie.data.repository.FavoriteRepo
import com.yogi.movie.data.repository.FavoriteRepoImpl
import com.yogi.movie.data.source.local.FavoriteLocalDataSource
import com.yogi.movie.features.favorite.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

val favoriteModule = module {

    fun provideFavoriteRepo(source: FavoriteLocalDataSource): FavoriteRepoImpl {
        return FavoriteRepoImpl(source)
    }

    single<FavoriteRepo> {
        provideFavoriteRepo(get())
    }

    viewModel {
        FavoriteViewModel(get())
    }

}