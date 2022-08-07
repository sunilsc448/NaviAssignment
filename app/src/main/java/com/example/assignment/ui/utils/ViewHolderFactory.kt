package com.example.assignment.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.ui.viewHolder.RecyclerBindingVH

object ViewHolderFactory {
    fun getViewHolder(resourceId: Int, parent: ViewGroup): RecyclerView.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, resourceId, parent, false)
        return RecyclerBindingVH(dataBinding)
    }
    fun bindViewHolder( resourceId:Int, holder:RecyclerView.ViewHolder, obj:Any?){
        if(obj == null)return
        when(resourceId){
            R.layout.pull_request_item -> {
                (holder as RecyclerBindingVH).bind(obj)
            }else -> {
                // for other future implementing views
            }
        }
    }
}