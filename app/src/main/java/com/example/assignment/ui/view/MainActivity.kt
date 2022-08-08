package com.example.assignment.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.R
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.ui.factory.ViewModelFactory
import com.example.assignment.ui.viewModel.MainViewModel
import com.example.assignment.BR
import com.example.assignment.data.api.USERNAME
import com.example.assignment.di.component.DaggerMainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel
    private lateinit var dataBinding:ActivityMainBinding
    @Inject lateinit var viewModelFactory:ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        DaggerMainComponent.builder().build().injectActivity(this)
        setupUI()
        setViewModel()
    }

    private fun setupUI() {
      val title = String.format(resources.getString(R.string.action_bar_title), USERNAME, resources.getString(R.string.Project))
      supportActionBar?.title = title
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        dataBinding.setVariable(BR.viewModel, viewModel)
    }
}