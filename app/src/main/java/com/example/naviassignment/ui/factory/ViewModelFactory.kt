package com.example.naviassignment.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.naviassignment.data.api.ApiHelper
import com.example.naviassignment.data.repository.MainRepository
import com.example.naviassignment.ui.viewmodel.MainViewModel

class ViewModelFactory(val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper = apiHelper)) as T
        }
        throw IllegalArgumentException("This viewModel is not supported")
    }
}