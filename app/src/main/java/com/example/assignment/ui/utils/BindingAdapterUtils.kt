package com.example.assignment.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.data.model.PullRequest
import com.example.assignment.ui.adapter.RecyclerViewAdapter

object BindingAdapterUtils {
      @BindingAdapter("bindList")
      @JvmStatic
      fun setPullRequestsAdapter(recyclerVw:RecyclerView, bindList: List<PullRequest>?){
            bindList?.let{
                  var adapter = recyclerVw.adapter
                  if (adapter == null){
                        adapter = RecyclerViewAdapter(it)
                        recyclerVw.adapter = adapter
                  }else{
                        (adapter as RecyclerViewAdapter).updateData(it)
//                        recyclerVw.scrollToPosition(adapter.itemCount - 1)
                  }
            }
      }

      @BindingAdapter("loadImage")
      @JvmStatic
      fun setImage(imageView: ImageView, url: String?) {
          Glide.with(imageView.context).load(url).placeholder(R.drawable.placeholder_image).into(imageView)
      }

      @BindingAdapter("setProgressBar")
      @JvmStatic
      fun setProgressBarVisibility(progressBar: ProgressBar, dataStatus: Status){
          progressBar.visibility = when(dataStatus){
                Status.LOADING -> View.VISIBLE
                else ->  View.GONE
          }
      }

      @BindingAdapter("setDataStatusText")
      @JvmStatic
      fun setDataStatusText(textView: TextView, dataStatus: Status){
            val text = when(dataStatus){
                  Status.LOADING -> {
                        textView.visibility = View.VISIBLE
                        textView.resources.getString(R.string.please_wait)
                  }
                  Status.ERROR ->
                  {
                        textView.visibility = View.VISIBLE
                        textView.resources.getString(R.string.error_txt)
                  }
                  Status.EMPTY -> {
                        textView.visibility = View.VISIBLE
                        textView.resources.getString(R.string.no_items_text)
                  }
                  else -> {
                        textView.visibility = View.GONE
                        ""
                  }
            }
            textView.text = text
      }
}