package com.example.assignment.ui.viewHolder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.BR
import com.example.assignment.data.model.PullRequest

class RecyclerBindingVH(private val binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Any?){
        if(item is PullRequest){
            binding.setVariable(BR.item, item)
        }
        //else if for other future implementing views
    }
}