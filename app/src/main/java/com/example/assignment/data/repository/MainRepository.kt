package com.example.assignment.data.repository

import com.example.assignment.data.api.ApiHelper
import com.example.assignment.data.model.PullRequest
import kotlinx.coroutines.CoroutineScope

class MainRepository(private val apiHelper: ApiHelper){
    suspend fun getClosedPullRequests(page:Int):List<PullRequest> = apiHelper.getClosedPullRequests(page)
}