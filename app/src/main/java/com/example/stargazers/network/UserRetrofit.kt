package com.example.stargazers.network

import com.example.stargazers.model.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

enum class StargazersApiStatus { LOADING, ERROR, DONE }



interface UserRetrofit {
    @GET("stargazers")
    suspend fun  getUsers(): Response<List<User>>
}


