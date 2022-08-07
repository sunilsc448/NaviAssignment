package com.example.naviassignment.data.api

import com.example.naviassignment.data.model.PullRequest
import retrofit2.http.GET

interface ApiService {
    @GET("repos/sunilsc448/NaviAssignment/pulls?state=closed")
    suspend fun getClosedPullRequests(): List<PullRequest>
}