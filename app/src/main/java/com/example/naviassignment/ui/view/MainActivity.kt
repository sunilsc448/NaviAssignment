package com.example.naviassignment.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.naviassignment.R
import com.example.naviassignment.data.api.ApiHelper
import com.example.naviassignment.data.api.RetrofitBuilder
import com.example.naviassignment.ui.factory.ViewModelFactory
import com.example.naviassignment.ui.viewmodel.MainViewModel
import com.example.naviassignment.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getClosedPullRequests().observe(this, {
            when(it.status){
                Status.LOADING -> {

                }
                Status.SUCCESS -> {

                }
                Status.ERROR -> {

                }
            }
        })
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }
}