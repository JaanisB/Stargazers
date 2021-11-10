package com.example.stargazers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stargazers.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME: String = "user_db"
    }



}