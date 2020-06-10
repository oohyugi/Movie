package com.yogi.movie.features.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.movie.R
import com.yogi.movie.core.model.MovieMdl

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class MovieListAdapter(val listener: MovieListAdapterListener) :
    ListAdapter<MovieMdl, MovieListAdapter.ViewHolder>(DiffUtilsMovieListAdapter()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.bind(data, listener)


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgMovie = itemView.findViewById<ImageView>(R.id.iv_movie)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        private val tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                return ViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_movie, parent, false)
                )
            }

        }

        fun bind(
            data: MovieMdl?,
            listener: MovieListAdapterListener
        ) {

            data?.let {
                Glide.with(itemView).load("https://image.tmdb.org/t/p/original${it.posterPath}")
                    .into(imgMovie)
                tvTitle.text = it.title
                tvDate.text = it.releaseDate
                tvDesc.text = it.overview

                itemView.setOnClickListener {
                    listener.onItemClickListener(data)
                }
            }


        }

    }

    class DiffUtilsMovieListAdapter : DiffUtil.ItemCallback<MovieMdl>() {
        override fun areItemsTheSame(oldItem: MovieMdl, newItem: MovieMdl): Boolean {
            // todo: return example id
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieMdl, newItem: MovieMdl): Boolean {

            return oldItem == newItem

        }

    }

    class MovieListAdapterListener(val clickListener: (item: MovieMdl?) -> Unit) {
        fun onItemClickListener(item: MovieMdl?) = clickListener(item)
    }


}