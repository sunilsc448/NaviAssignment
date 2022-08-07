package com.example.naviassignment.data.repository

import com.example.naviassignment.data.api.ApiHelper
import com.example.naviassignment.data.model.PullRequest
import com.example.naviassignment.utils.RemoteDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainRepository(val apiHelper: ApiHelper){
    suspend fun getClosedPullRequests(scope:CoroutineScope): RemoteDataResponse {
        try{
            var data:List<PullRequest> = emptyList()
            scope.launch {
                data = apiHelper.getClosedPullRequests()
            }
            return RemoteDataResponse.SuccessResponse(data = data)
        }catch (exception:Exception){
            return RemoteDataResponse.ErrorResponse(exception = exception)
        }
    }
}