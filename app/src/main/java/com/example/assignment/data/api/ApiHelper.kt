package com.example.assignment.data.api

import com.example.assignment.data.repository.IRemoteDataSource
import javax.inject.Inject

class ApiHelper(private val apiService:ApiService):IRemoteDataSource {
    override suspend fun getClosedPullRequests(page:Int) = apiService.getClosedPullRequests(page)
}
