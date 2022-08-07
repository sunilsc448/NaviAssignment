package com.example.naviassignment.data.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    val title:String,
    val created_at:String,
    val closed_at:String,
    val user:User
)
