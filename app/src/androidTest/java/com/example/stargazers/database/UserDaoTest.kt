package com.example.stargazers.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.stargazers.model.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
// Adds "JUnit" library that tests Java/Kotlin code
@RunWith(AndroidJUnit4::class)
// Tell "Junit" that this will be Unit test
@SmallTest
class UserDaoTest {

    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDao



    // Executed before every test
    @Before
    fun setup() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        //Acces Room Db from Main Thread, so that are no manipulating between threads
        ).allowMainThreadQueries().build()
        userDao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val user = User (1, "testLogin", "https://avatars.githubusercontent.com/u/4012103?v=4", "User", false)
        userDao.insert(user)

        val allUsers = userDao.getUserList()

        assertThat(allUsers).contains(user)

    }



}