package com.example.naviassignment.ui.viewmodel

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.naviassignment.data.repository.MainRepository
import com.example.naviassignment.utils.RemoteDataResponse
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getClosedPullRequests() = liveData(Dispatchers.IO){
        emit(RemoteDataResponse.Response())
        try {
            emit(RemoteDataResponse.SuccessResponse(data = mainRepository.getClosedPullRequests(viewModelScope)))
        } catch (exception: Exception) {
            emit(RemoteDataResponse.ErrorResponse(exception = exception))
        }
    }
}