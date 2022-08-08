package com.example.assignment.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.R
import com.example.assignment.data.api.ApiHelper
import com.example.assignment.data.api.RetrofitBuilder
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.ui.factory.ViewModelFactory
import com.example.assignment.ui.viewModel.MainViewModel
import com.example.assignment.BR
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel
    private lateinit var dataBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        setupUI()
        setViewModel()
//        initObservers()
    }

    private fun setupUI() {
      supportActionBar?.setTitle(resources.getString(R.string.action_bar_title))

    }

//    private fun initObservers() {
//        viewModel.getDataStatus().observe(this, { status ->
//            when(status){
//                Status.EMPTY -> {
//                    viewModel.fetchClosedPullRequests()
//                }
//                Status.SUCCESS -> {
//                    println("success")
//                }
//                Status.ERROR -> {
//                    println("failure")
//                }
//            }
//        })
//    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
        dataBinding.setVariable(BR.viewModel, viewModel)
    }
}