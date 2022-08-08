package com.example.assignment.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.data.repository.MainRepository
import com.example.assignment.ui.viewModel.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("This viewModel is not supported")
    }
}