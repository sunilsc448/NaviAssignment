package com.example.assignment.data.repository

import com.example.assignment.data.model.PullRequest

interface IRemoteDataSource {
    suspend fun getClosedPullRequests(page:Int):List<PullRequest>
}