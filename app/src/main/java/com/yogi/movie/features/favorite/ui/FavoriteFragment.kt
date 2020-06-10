package com.yogi.movie.features.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.movie.R
import com.yogi.movie.core.base.BaseFragment
import com.yogi.movie.core.base.BaseViewState
import com.yogi.movie.features.home.ui.MovieListAdapter
import kotlinx.android.synthetic.main.favorite_fragment.*
import kotlinx.android.synthetic.main.info_view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment() {


    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mContext as AppCompatActivity?)?.supportActionBar?.title = "Favorite"

        initListFavorite()
        favoriteViewModel.loadFavorite()
        initObserve()
    }

    private fun initObserve() {
        favoriteViewModel.apply {
            responseGetFavorite.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Success -> {
                        movieAdapter.submitList(it.data)
                    }
                    is BaseViewState.Error -> {
                        info_view?.visibility = View.VISIBLE
                        tv_error?.text = it.errorMessage
                    }
                }
            })
        }
    }

    private fun initListFavorite() {
        movieAdapter = MovieListAdapter(MovieListAdapter.MovieListAdapterListener {

            var bundle = bundleOf("item" to it)
            findNavController().navigate(R.id.action_favorite_to_DetailFragment, bundle)

        })
        val mLayoutManager = LinearLayoutManager(activity)

        rv_movie?.apply {
            layoutManager = mLayoutManager
            adapter = movieAdapter
        }
    }


}
