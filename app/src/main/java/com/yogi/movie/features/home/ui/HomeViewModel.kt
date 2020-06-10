package com.yogi.movie.features.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.movie.core.base.BaseViewState
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.repository.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeViewModel(private val homeRepo: HomeRepo) : ViewModel() {


    private val _responseHome = MutableLiveData<BaseViewState<List<MovieMdl>>>()
    val responseDataHome: LiveData<BaseViewState<List<MovieMdl>>> = _responseHome
    private var mPage: Int = 1
    private var maxPage = 0
    private var mlist: MutableList<MovieMdl> = mutableListOf()

    init {
        loadMovie("popular", 1)
    }

    fun loadMovie(category: String, page: Int, isLoadMore: Boolean = false) {
        _responseHome.value = BaseViewState.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                homeRepo.getHome(category, page)
            }
            when (result) {
                is ResultState.Success -> {

                    result.data?.let {
                        maxPage = it.totalPages
                        mlist.addAll(it.results!!)
                    }

                    if (!isLoadMore) _responseHome.value =
                        BaseViewState.Success(result.data?.results)
                    else _responseHome.value = BaseViewState.Success(mlist)


                }
                is ResultState.Error -> _responseHome.value =
                    BaseViewState.Error(result.errorMessage)
            }
        }

    }

    fun loadMoreData(category: String) {

        if (mPage < maxPage) {
            mPage++
            loadMovie(category = category, page = mPage, isLoadMore = true)
        }


    }

    fun resetAndLoadMovie(category: String) {

        mlist.clear()
        mPage = 1
        loadMovie(category = category, page = mPage, isLoadMore = true)


    }
//    private val _navigateToDetail = SingleLiveEvent<ProductPromoItemMdl>()
//    val navigateToDetail: SingleLiveEvent<ProductPromoItemMdl> get() =_navigateToDetail

}
