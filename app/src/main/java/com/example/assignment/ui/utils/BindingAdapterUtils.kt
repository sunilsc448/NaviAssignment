package com.example.assignment.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.model.PullRequest
import com.example.assignment.ui.adapter.RecyclerViewAdapter
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.ui.viewModel.MainViewModel

object BindingAdapterUtils {
      @BindingAdapter("bindList", "viewModel")
      @JvmStatic
      fun setPullRequestsAdapter(recyclerVw:RecyclerView, bindList: List<PullRequest>?, viewModel: MainViewModel){
            bindList?.let{
                  var adapter = recyclerVw.adapter
                  if (adapter == null){
                        adapter = RecyclerViewAdapter(it)
                        recyclerVw.adapter = adapter
                        val linearLayoutManager:LinearLayoutManager = recyclerVw.layoutManager as LinearLayoutManager
                        recyclerVw.addOnScrollListener(object: PaginationScrollListener(linearLayoutManager) {
                              override fun loadMoreItems(){
                                    viewModel.loadMore()
                              }

                              override fun isLastPage(): Boolean {
                                  return viewModel.isLastPage
                              }

                              override fun isLoading(): Boolean {
                                    return viewModel.blockContinuousPagination
                              }
                        })
                  }else{
                        (adapter as RecyclerViewAdapter).updateData(it)
                  }
            }
      }

      @BindingAdapter("loadImage")
      @JvmStatic
      fun setImage(imageView: ImageView, url: String?) {
          Picasso.with(imageView.context).load(url).placeholder(R.drawable.placeholder_image).into(imageView)
      }

      @BindingAdapter("setProgressBar")
      @JvmStatic
      fun setProgressBarVisibility(progressBar: ProgressBar, dataStatus: Status){
          progressBar.visibility = when(dataStatus){
                Status.LOADING -> View.VISIBLE
                else ->  View.GONE
          }
      }

      @BindingAdapter("setDataStatusText", "list")
      @JvmStatic
      fun setDataStatusText(textView: TextView, dataStatus: Status, list: List<PullRequest>?){
            list?.let {
                  if(it.isNotEmpty()){
                        textView.visibility = View.GONE
                        return
                  }
            }
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