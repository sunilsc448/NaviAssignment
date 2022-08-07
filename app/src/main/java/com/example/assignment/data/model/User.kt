package com.example.assignment.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")val user_name:String,
    @SerializedName("avatar_url")val avatar:String
)
