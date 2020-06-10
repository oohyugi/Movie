package com.yogi.movie.features.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.movie.core.base.BaseViewState
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.repository.FavoriteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(private val favoriteRepo: FavoriteRepo) : ViewModel() {

    private val _responseGetFavorite = MutableLiveData<BaseViewState<List<MovieMdl>>>()
    val responseGetFavorite: LiveData<BaseViewState<List<MovieMdl>>> = _responseGetFavorite


    fun loadFavorite() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                favoriteRepo.getAllFavorite()
            }
            when (result) {
                is ResultState.Success -> {


                    if (result.data!!.isNotEmpty()) {
                        _responseGetFavorite.value =
                            BaseViewState.Success(result.data)
                    } else {
                        _responseGetFavorite.value =
                            BaseViewState.Error("Data Empty")
                    }


                }
                is ResultState.Error -> _responseGetFavorite.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }
    }


}
