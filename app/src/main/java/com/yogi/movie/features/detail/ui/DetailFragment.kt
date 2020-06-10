package com.yogi.movie.features.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yogi.movie.R
import com.yogi.movie.core.base.BaseFragment
import com.yogi.movie.core.base.BaseViewState
import com.yogi.movie.core.model.MovieMdl
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

const val ARG_ITEM = "item"

class DetailFragment : BaseFragment() {


    private val detailViewModel: DetailViewModel by viewModel()
    private var isFavorite = false
    private lateinit var reviewListAdapter: ReviewListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getSerializable(ARG_ITEM) as MovieMdl
//        (activity as AppCompatActivity?)?.supportActionBar?.title = item.title
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500${item.backdropPath}")
            .into(iv_backdrop)
        tv_title?.text = item.title
        tv_date?.text = item.releaseDate
        tv_desc?.text = item.overview

        initListReview()
        detailViewModel.loadDetailData(item.id)
        detailViewModel.loadReviewa(item.id)
        detailViewModel.getFavoriteById(item.id)
        iv_favorite?.setOnClickListener {


            if (!isFavorite) {
                detailViewModel.setFavorite(item)
            } else {
                detailViewModel.deleteFavorite(item.id)
            }

        }

        initObserver()
    }

    private fun initListReview() {
        var mLayoutManager = LinearLayoutManager(mContext)
        reviewListAdapter = ReviewListAdapter(ReviewListAdapter.ReviewListAdapterListener {

        })
        rv_review?.apply {
            layoutManager = mLayoutManager
            adapter = reviewListAdapter
        }
    }

    private fun initObserver() {
        detailViewModel.apply {
            responseSetFavorite.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Success -> {
                        isFavorite = true
                        Toast.makeText(mContext, "Add to favorite Success", Toast.LENGTH_SHORT)
                            .show()
                        iv_favorite?.setImageResource(R.drawable.ic_favorite_red_24dp)
                    }
                    is BaseViewState.Error -> Toast.makeText(
                        mContext,
                        "Add to favorite Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            responseDeleteFavorite.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Success -> {
                        isFavorite = false
                        Toast.makeText(mContext, "Delete Favorite Success", Toast.LENGTH_SHORT)
                            .show()
                        iv_favorite?.setImageResource(R.drawable.ic_favorite_border_red_24dp)
                    }

                }
            })

            responseGetFavoriteById.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Success -> {
                        isFavorite = true
                        iv_favorite?.setImageResource(R.drawable.ic_favorite_red_24dp)
                    }
                    is BaseViewState.Error -> {
                        isFavorite = false
                        iv_favorite?.setImageResource(R.drawable.ic_favorite_border_red_24dp)
                    }
                }
            })
            responseReview.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Success -> {
                        reviewListAdapter.submitList(it.data)
                    }

                }
            })
        }
    }


}
