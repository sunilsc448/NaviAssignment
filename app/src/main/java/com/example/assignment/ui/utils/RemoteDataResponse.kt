package com.example.assignment.ui.utils

import com.example.assignment.data.model.PullRequest

sealed class RemoteDataResponse {
    abstract val status: Status
    class SuccessResponse(override val status: Status = Status.SUCCESS, val data:List<PullRequest>): RemoteDataResponse()
    class ErrorResponse(override var status: Status = Status.API_ERROR): RemoteDataResponse()
}