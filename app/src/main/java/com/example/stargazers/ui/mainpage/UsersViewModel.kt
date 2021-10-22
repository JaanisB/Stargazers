package com.example.stargazers.ui.mainpage

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.stargazers.model.User
import com.example.stargazers.network.StargazersApiStatus
import com.example.stargazers.network.UserNetworkEntity
import com.example.stargazers.repository.MainRepository
import com.example.stargazers.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel
@Inject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository
) : ViewModel() {


    private val _users: MutableLiveData<List<User>> = MutableLiveData()

    val users: LiveData<List<User>>
        get() = _users

    init {
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {

            mainRepository.getUser()
                .onEach { users ->
                    _users.value = users
                }.launchIn(viewModelScope)
        }
    }




/*    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response*/


/*
// Variable for users list from Github API
private val _users = MutableLiveData<List<UserNetworkEntity>>()

val users: LiveData<List<UserNetworkEntity>>
    get() = _users
*/


/*
    // Variable for API status
    private val _status = MutableLiveData<StargazersApiStatus>()

    val status: LiveData<StargazersApiStatus>
        get() = _status
*/



/*private fun getStargazerUsers() {
    viewModelScope.launch {
        _status.value = StargazersApiStatus.LOADING
        try {
            _users.value = StargazersApi.retrofitService.getProperties()
            Log.d("n_response", _users.value.toString())
            _status.value = StargazersApiStatus.DONE
        } catch (e: Exception) {
            _status.value = StargazersApiStatus.ERROR
            _users.value = ArrayList()
        }
    }
}*/
}