package com.example.stargazers.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

enum class StargazersApiStatus { LOADING, ERROR, DONE }

interface UserRetrofit {
    @GET("stargazers")
    suspend fun getUsers(): List<UserNetworkEntity>
}


