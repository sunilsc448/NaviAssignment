package com.example.naviassignment.data.api

import com.example.naviassignment.data.repository.IRemoteDataSource
import kotlinx.coroutines.CoroutineScope

class ApiHelper(private val apiService:ApiService):IRemoteDataSource {
    override suspend fun getClosedPullRequests() = apiService.getClosedPullRequests()
}
