package com.yogi.movie.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yogi.movie.R
import com.yogi.movie.core.model.CategoryMovieMdl
import kotlinx.android.synthetic.main.category_fragment_dialog.*
import kotlinx.android.synthetic.main.category_item.view.*

const val ARG_ITEM_LIST = "item_list"


@Suppress("UNCHECKED_CAST")
class ItemListDialogFragment(val clickListener: OnOptionCLickListener) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.layoutManager = LinearLayoutManager(context)
        val itemList = arguments?.getSerializable(ARG_ITEM_LIST) as ArrayList<CategoryMovieMdl>
        list.adapter = ItemAdapter(itemList)
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.category_item,
            parent,
            false
        )
    ) {

        internal val text: TextView = itemView.text
    }

    private inner class ItemAdapter internal constructor(private val mItemList: ArrayList<CategoryMovieMdl>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var item = mItemList[position]
            holder.text.text = item.title
            holder.itemView.setOnClickListener {
                clickListener.onManageClick(item)
                dialog?.dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mItemList.size
        }
    }

    companion object {

        fun newInstance(
            clickListener: OnOptionCLickListener,
            itemList: ArrayList<CategoryMovieMdl>
        ): ItemListDialogFragment =
            ItemListDialogFragment(clickListener).apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ITEM_LIST, itemList)
                }
            }

    }

    interface OnOptionCLickListener {
        fun onManageClick(category: CategoryMovieMdl?)
    }
}
