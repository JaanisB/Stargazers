package com.example.stargazers.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stargazers.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users")
    suspend fun getUserList(): List<User>

    @Query ("SELECT * FROM users WHERE login = :login")
    suspend fun getUserbyName(login: String): User?
}