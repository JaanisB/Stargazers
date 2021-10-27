package com.example.stargazers.repository

import com.example.stargazers.database.CacheMapper
import com.example.stargazers.database.UserDao
import com.example.stargazers.model.User
import com.example.stargazers.network.NetworkMapper
import com.example.stargazers.network.UserRetrofit
import com.example.stargazers.util.DataState
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val userDao: UserDao,
    private val userRetrofit: UserRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getUser(): kotlinx.coroutines.flow.Flow<List<User>> = flow {

/*        try {*/
            val networkUsers = userRetrofit.getUsers()
            val users = networkMapper.mapFromEntityList(networkUsers)
            for (user in users) {
                userDao.insert(cacheMapper.mapToEntity(user))
            }
            val cachedUsers = userDao.get()
            emit((cacheMapper.mapFromEntityList(cachedUsers)))
/*        } catch (e: Exception) {
            (e.toString())
        }*/
    }
}