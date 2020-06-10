package com.yogi.movie.features.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.movie.core.base.BaseViewState
import com.yogi.movie.core.model.DetailMovieMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.model.ReviewsMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.repository.DetailRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val detailRepo: DetailRepo) : ViewModel() {


    private val _responseDetailData = MutableLiveData<BaseViewState<DetailMovieMdl>>()
    val responseDetailData: LiveData<BaseViewState<DetailMovieMdl>> = _responseDetailData

    private val _responseReview = MutableLiveData<BaseViewState<List<ReviewsMdl>>>()
    val responseReview: LiveData<BaseViewState<List<ReviewsMdl>>> = _responseReview

    private val _responseSetFavorite = MutableLiveData<BaseViewState<String>>()
    val responseSetFavorite: LiveData<BaseViewState<String>> = _responseSetFavorite

    private val _responseDeleteFavorite = MutableLiveData<BaseViewState<String>>()
    val responseDeleteFavorite: LiveData<BaseViewState<String>> = _responseDeleteFavorite

    private val _responseGetFavoriteById = MutableLiveData<BaseViewState<MovieMdl>>()
    val responseGetFavoriteById: LiveData<BaseViewState<MovieMdl>> = _responseGetFavoriteById

    fun loadDetailData(movieId: Int) {

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                detailRepo.getDetail(movieId)
            }
            when (result) {
                is ResultState.Success -> {


                    _responseDetailData.value =
                        BaseViewState.Success(result.data)


                }
                is ResultState.Error -> _responseDetailData.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }
    }

    fun loadReviewa(movieId: Int) {

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                detailRepo.getReviews(movieId)
            }
            when (result) {
                is ResultState.Success -> {


                    _responseReview.value =
                        BaseViewState.Success(result.data?.results)


                }
                is ResultState.Error -> _responseReview.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }
    }

    fun getFavoriteById(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                detailRepo.getFavoriteById(id)
            }
            when (result) {
                is ResultState.Success -> {


                    if (result.data != null) {
                        _responseGetFavoriteById.value =
                            BaseViewState.Success(result.data)
                    } else {
                        _responseGetFavoriteById.value =
                            BaseViewState.Error("Empty")
                    }


                }
                is ResultState.Error -> _responseGetFavoriteById.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                detailRepo.deleteFavorite(id)
            }
            when (result) {
                is ResultState.Success -> {


                    _responseDeleteFavorite.value =
                        BaseViewState.Success(result.data)


                }
                is ResultState.Error -> _responseDeleteFavorite.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }
    }

    fun setFavorite(item: MovieMdl) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                detailRepo.setFavorite(item)
            }
            when (result) {
                is ResultState.Success -> {


                    _responseSetFavorite.value =
                        BaseViewState.Success(result.data)


                }
                is ResultState.Error -> _responseSetFavorite.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }

    }
}
