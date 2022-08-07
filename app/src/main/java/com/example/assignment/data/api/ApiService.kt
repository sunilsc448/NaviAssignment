package com.example.assignment.data.api

import com.example.assignment.data.model.PullRequest
import retrofit2.http.GET

interface ApiService {
    @GET("repos/sunilsc448/NaviAssignment/pulls?state=closed")
    suspend fun getClosedPullRequests(): List<PullRequest>
}