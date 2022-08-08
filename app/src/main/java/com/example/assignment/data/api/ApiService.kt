package com.example.assignment.data.api

import com.example.assignment.data.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pulls?state=closed&per_page=$PER_PAGE")
    suspend fun getClosedPullRequests(@Query("page")page:Int): List<PullRequest>
}