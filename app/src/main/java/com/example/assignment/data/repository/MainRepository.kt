package com.example.assignment.data.repository

import com.example.assignment.data.api.ApiService
import com.example.assignment.data.model.PullRequest
import javax.inject.Inject

class MainRepository @Inject constructor (val apiService: ApiService){
    suspend fun getClosedPullRequests(page:Int):List<PullRequest> = apiService.getClosedPullRequests(page)
}