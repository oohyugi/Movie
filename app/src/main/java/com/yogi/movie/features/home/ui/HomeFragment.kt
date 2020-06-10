package com.yogi.movie.features.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.movie.R
import com.yogi.movie.core.base.BaseFragment
import com.yogi.movie.core.base.BaseViewState
import com.yogi.movie.core.model.CategoryMovieMdl
import com.yogi.movie.core.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.info_view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeFragment : BaseFragment() {


    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var movieAdapter: MovieListAdapter

    private var mCategoryName = "popular"
    private var mCategoryTitle = "Popular"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Movie - $mCategoryTitle"
        setHasOptionsMenu(true)
        initListHome()
        initObserve()

        btn_category?.setOnClickListener {
            val bottomSheetFragment = ItemListDialogFragment.newInstance(
                object : ItemListDialogFragment.OnOptionCLickListener {
                    override fun onManageClick(category: CategoryMovieMdl?) {
                        category?.let {
                            mCategoryName = it.name.toLowerCase(Locale.getDefault())
                            mCategoryTitle = it.title
                            (activity as AppCompatActivity?)?.supportActionBar?.title =
                                "Movie - $mCategoryTitle"
                            homeViewModel.resetAndLoadMovie(mCategoryName)
                        }

                    }

                }, arrayListOf(
                    CategoryMovieMdl("Popular", "popular"),
                    CategoryMovieMdl("Upcomming", "upcoming"),
                    CategoryMovieMdl("Top Rated", "top_rated"),
                    CategoryMovieMdl("Now Playing", "now_playing")
                )
            )
            bottomSheetFragment.show(
                (activity as AppCompatActivity).supportFragmentManager,
                bottomSheetFragment.tag
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_favorite -> {
                findNavController().navigate(R.id.action_home_to_FavoriteFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initObserve() {
        homeViewModel.apply {
            responseDataHome.observe(this@HomeFragment, Observer { result ->
                when (result) {
                    is BaseViewState.Loading -> progress_circular?.visibility = View.VISIBLE
                    is BaseViewState.Error -> {
                        hideLoading()
                        info_view?.visibility = View.VISIBLE
                        tv_error?.text = result.errorMessage
                    }
                    is BaseViewState.Success -> {
                        hideLoading()
                        result.data?.let { listMovie ->
                            movieAdapter.submitList(listMovie)

                        }
                    }
                }
            })
        }
    }

    private fun hideLoading() {
        progress_circular?.visibility = View.GONE
    }

    private fun initListHome() {
        movieAdapter = MovieListAdapter(MovieListAdapter.MovieListAdapterListener {

            var bundle = bundleOf("item" to it)
            findNavController().navigate(R.id.action_home_to_DetailFragment, bundle)

        })
        val mLayoutManager = LinearLayoutManager(activity)

        var scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                homeViewModel.loadMoreData(mCategoryName)
            }

        }
        rv_movie?.apply {
            layoutManager = mLayoutManager
            adapter = movieAdapter
            addOnScrollListener(scrollListener)
        }
    }

}
