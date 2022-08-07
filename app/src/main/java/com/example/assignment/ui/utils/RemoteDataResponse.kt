package com.example.assignment.ui.utils

import com.example.assignment.data.model.PullRequest
import java.lang.Exception

sealed class RemoteDataResponse {
    abstract val status: Status
    open class Response(override val status: Status = Status.LOADING) : RemoteDataResponse()
    class SuccessResponse(override val status: Status = Status.SUCCESS, val data:List<PullRequest>):
        Response()
    class ErrorResponse(override val status: Status = Status.ERROR, val exception: Exception):
        RemoteDataResponse()
}