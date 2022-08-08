package com.example.assignment.di.module

import com.example.assignment.data.api.ApiService
import com.example.assignment.data.api.BASE_URL
import com.example.assignment.di.scope.ComponentScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    @Provides
    @ComponentScope
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    @ComponentScope
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).
        client(okHttpClient).
        addConverterFactory(GsonConverterFactory.create()).
        build()
    }

    @Provides
    @ComponentScope
    fun getApiServive(retrofit:Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}