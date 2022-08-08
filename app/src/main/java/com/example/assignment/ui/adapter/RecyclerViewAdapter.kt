package com.example.assignment.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.model.PullRequest
import com.example.assignment.ui.utils.ViewHolderFactory

class RecyclerViewAdapter(var mList:List<Any>?):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        if(mList == null)return -1
        return when (mList!![position]) {
            is PullRequest -> {
                R.layout.pull_request_item
            }
            else->{
                throw IllegalArgumentException("Layout is missing for the drawing view holder")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = ViewHolderFactory.getViewHolder(viewType, parent)
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        ViewHolderFactory.bindViewHolder(getItemViewType(position), holder, mList?.get(position))
    }

    override fun getItemCount() = mList?.size ?: 0

    fun updateData(list:List<Any>?){
        val prevCount:Int = getItemCount()
        mList = list
        notifyItemRangeChanged(prevCount, getItemCount())
    }
}