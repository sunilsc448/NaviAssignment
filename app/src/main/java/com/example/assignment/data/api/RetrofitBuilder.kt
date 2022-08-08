package com.example.assignment.data.api

import android.content.Context
import com.example.assignment.network.NetworkInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**Constants for Github API,
 * Change the Configuration her for other
 * account and repo*/
object RetrofitBuilder {
    private fun getRetrofit(httpClient: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(factory)
            .build()
    }

    private fun getClient():OkHttpClient = OkHttpClient.Builder().build()
    private fun getFactory():Converter.Factory = GsonConverterFactory.create()
    fun getApiService():ApiService = getRetrofit(getClient(), getFactory()).create(ApiService::class.java)
}