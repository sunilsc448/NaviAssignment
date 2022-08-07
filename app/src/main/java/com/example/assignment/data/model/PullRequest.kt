package com.example.assignment.data.model

data class PullRequest(
    val title:String,
    val created_at:String,
    val merged_at:String,
    val user:User
)
