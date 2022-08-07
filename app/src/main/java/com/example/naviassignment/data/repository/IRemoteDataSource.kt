package com.example.naviassignment.data.repository

import com.example.naviassignment.data.model.PullRequest
import kotlinx.coroutines.CoroutineScope

interface IRemoteDataSource {
    suspend fun getClosedPullRequests():List<PullRequest>
}