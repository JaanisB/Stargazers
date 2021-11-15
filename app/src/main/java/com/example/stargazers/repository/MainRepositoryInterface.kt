package com.example.stargazers.repository

import com.example.stargazers.model.User
import com.example.stargazers.util.Resource

interface MainRepositoryInterface {

    suspend fun getUsers() : Resource<List<User>>

    suspend fun getUserbyName(userName: String): User?


}