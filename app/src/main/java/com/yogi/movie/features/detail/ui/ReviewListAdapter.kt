package com.yogi.movie.features.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogi.movie.R
import com.yogi.movie.core.model.ReviewsMdl

/**
 * Created by Yogi Putra on 11/06/20.
 * Github : https://github.com/oohyugi
 */

class ReviewListAdapter(val listener: ReviewListAdapterListener) :
    ListAdapter<ReviewsMdl, ReviewListAdapter.ViewHolder>(DiffUtilsReviewListAdapter()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.bind(data, listener)


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvAuthor = itemView.findViewById<TextView>(R.id.tv_author)
        var tvContent = itemView.findViewById<TextView>(R.id.tv_content)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                return ViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_review, parent, false)
                )
            }

        }

        fun bind(
            data: ReviewsMdl?,
            listener: ReviewListAdapterListener
        ) {

            data?.let {
                tvAuthor.text = it.author
                tvContent.text = it.content
            }


        }

    }

    class DiffUtilsReviewListAdapter : DiffUtil.ItemCallback<ReviewsMdl>() {
        override fun areItemsTheSame(oldItem: ReviewsMdl, newItem: ReviewsMdl): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewsMdl, newItem: ReviewsMdl): Boolean {

            return oldItem == newItem

        }

    }

    class ReviewListAdapterListener(val clickListener: (item: ReviewsMdl?) -> Unit) {
        fun onItemClickListener(item: ReviewsMdl?) = clickListener(item)
    }


}