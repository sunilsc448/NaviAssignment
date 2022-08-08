package com.example.assignment.data.repository

import android.accounts.NetworkErrorException
import com.example.assignment.data.api.ApiService
import com.example.assignment.ui.utils.RemoteDataResponse
import com.example.assignment.ui.utils.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class MainRepository @Inject constructor (private val apiService: ApiService){
    suspend fun getClosedPullRequests(page:Int, dispatcher: CoroutineDispatcher):RemoteDataResponse{
        return withContext(dispatcher) {
            try{
                RemoteDataResponse.SuccessResponse(data = apiService.getClosedPullRequests(page))
            }catch (throwable: Throwable){
                when(throwable){
                    is NetworkErrorException, is UnknownHostException ->
                        RemoteDataResponse.ErrorResponse(status =  Status.NETWORK_ERROR)
                    else -> RemoteDataResponse.ErrorResponse(status = Status.API_ERROR)
                }
            }
        }
    }
}