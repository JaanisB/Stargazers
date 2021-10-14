package com.example.stargazers.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

enum class StargazersApiStatus { LOADING, ERROR, DONE }

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL ="https://api.github.com/repos/android/architecture-samples/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface StargazersApiService {
    @GET("stargazers")
    suspend fun getProperties(): List<UserNetworkEntity>

}

object StargazersApi {
    val retrofitService: StargazersApiService by lazy {
        retrofit.create(StargazersApiService::class.java)
    }
}

