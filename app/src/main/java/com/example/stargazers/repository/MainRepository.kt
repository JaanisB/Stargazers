package com.example.stargazers.repository


import androidx.lifecycle.asLiveData
import com.example.stargazers.database.UserDao
import com.example.stargazers.model.User
import com.example.stargazers.network.UserRetrofit
import com.example.stargazers.util.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val userDao: UserDao,
    private val userRetrofit: UserRetrofit,
) : MainRepositoryInterface {


    // Get all users from dB
    override suspend fun getUsers(): Resource<List<User>> {


        Resource.Loading(null)

        //Try to get data from retrofit
        return try {
            val response = userRetrofit.getUsers()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                userDao.deleteAllUsers()
                for (user in result) {
                    userDao.insert(user)
                }
                Resource.Success(result)
            } else {
                Resource.Error( userDao.getUserList(), response.message())
            }


        } catch (e: Exception) {
            Resource.Error( userDao.getUserList(), (e.message ?: "Error occurred, data loaded from Room Db"))
        }
    }

    // get single user by its name from local room dB
    override suspend fun getUserbyName(userName: String): User? {
        return userDao.getUserbyName(userName)
    }
}