package com.example.naviassignment.data.repository

import com.example.naviassignment.data.api.ApiHelper
import com.example.naviassignment.data.model.PullRequest
import com.example.naviassignment.utils.RemoteDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainRepository(private val apiHelper: ApiHelper){
    suspend fun getClosedPullRequests(scope:CoroutineScope):List<PullRequest> = apiHelper.getClosedPullRequests()
}