package com.example.stargazers.ui.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stargazers.network.StargazersApi
import com.example.stargazers.network.StargazersApiStatus
import com.example.stargazers.network.UserNetworkEntity
import kotlinx.coroutines.launch


class UsersViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response


    // Variable for users list from Github API
    private val _users = MutableLiveData<List<UserNetworkEntity>>()

    val users: LiveData<List<UserNetworkEntity>>
        get() = _users


    // Variable for API status
    private val _status = MutableLiveData<StargazersApiStatus>()

    val status: LiveData<StargazersApiStatus>
    get() = _status


    init {
        getStargazerUsers()
    }

    private fun getStargazerUsers() {
        viewModelScope.launch {
            _status.value = StargazersApiStatus.LOADING
            try {
                _users.value = StargazersApi.retrofitService.getProperties()
                _status.value = StargazersApiStatus.DONE
            } catch (e: Exception) {
                _status.value = StargazersApiStatus.ERROR
                _users.value = ArrayList()
            }
        }
    }
}