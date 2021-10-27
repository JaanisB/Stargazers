package com.example.stargazers.ui.userdetails


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stargazers.model.User


class UserDetailViewModel(@Suppress("UNUSED_PARAMETER")user: User, app: Application) : AndroidViewModel(app) {

    private val _selectedUser = MutableLiveData<User>()
    val selectedUser: LiveData<User>
        get() = _selectedUser

    init {
        _selectedUser.value = user
    }

}



