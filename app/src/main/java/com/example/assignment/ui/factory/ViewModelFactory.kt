package com.example.assignment.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.data.api.ApiHelper
import com.example.assignment.data.repository.MainRepository
import com.example.assignment.ui.viewModel.MainViewModel

class ViewModelFactory(val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper = apiHelper)) as T
        }
        throw IllegalArgumentException("This viewModel is not supported")
    }
}