package com.example.assignment.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**Constants for Github API,
 * Change the Configuration her for other
 * account and repo*/
const val USERNAME = "sunilsc448"
const val REPO_NAME = "NaviAssignment"
const val PER_PAGE = 10

private const val BASE_URL = "https://api.github.com/repos/$USERNAME/$REPO_NAME"
object RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}