package com.example.stargazers.ui.userdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stargazers.model.User

class UserDetailViewModelFactory(
    private val user: User,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(user, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}