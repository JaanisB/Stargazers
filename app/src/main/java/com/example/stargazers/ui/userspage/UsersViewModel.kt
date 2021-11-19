package com.example.stargazers.ui.userspage

import androidx.lifecycle.*
import com.example.stargazers.model.User
import com.example.stargazers.repository.MainRepository
import com.example.stargazers.repository.MainRepositoryInterface
import com.example.stargazers.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel
@Inject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    sealed class UserEvent {
        class Success(resultUsers: List<User>) : UserEvent()
        class Failure(errorText: String) : UserEvent()
        object Loading : UserEvent()
        object Empty : UserEvent()
    }

    private val _userState = MutableStateFlow<UserEvent>(UserEvent.Empty)
    val userState: StateFlow<UserEvent> = _userState

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>>
        get() = _users

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user


    // Get single user from repo
    fun getUserByLogin(login: String) {
        viewModelScope.launch {
            _user.value = mainRepository.getUserbyName(login)
            if (_user.value != null) {
                _navigateToSelectedUser.value = _user.value
            }
        }
    }

    // Get all users from repo
    fun getUserList() {
        viewModelScope.launch {
            _userState.value = UserEvent.Loading

            when (val userResponse = mainRepository.getUsers()) {

                is Resource.Success -> {
                    _userState.value = UserEvent.Success(userResponse.data!!)
                    _users.value = userResponse.data!!
                }

                is Resource.Loading -> {
                    _userState.value = UserEvent.Loading
                }

                is Resource.Error -> {
                    _userState.value = UserEvent.Failure(userResponse.message!!)
                    _users.value = userResponse.data!!
                }

            }
        }
    }


    private val _navigateToSelectedUser = MutableLiveData<User>()
    val navigateToSelectedUser: LiveData<User>
        get() = _navigateToSelectedUser


    fun displayUserDetails(user: User) {
        _navigateToSelectedUser.value = user
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null!!
    }
}






