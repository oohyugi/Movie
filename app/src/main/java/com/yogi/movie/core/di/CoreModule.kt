package com.yogi.movie.core.di

import com.yogi.movie.core.utils.PrefHelper
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

val coreModule = module {

    single {
        PrefHelper(context = get())
    }

}