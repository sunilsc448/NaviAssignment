package com.example.naviassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.naviassignment.data.repository.MainRepository
import com.example.naviassignment.utils.RemoteDataResponse
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getClosedPullRequests() = liveData{
        emit(RemoteDataResponse.Response())
        emit(mainRepository.getClosedPullRequests(viewModelScope))
    }
}