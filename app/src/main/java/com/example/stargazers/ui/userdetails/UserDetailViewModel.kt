package com.example.stargazers.ui.userdetails


import android.app.Application
import androidx.lifecycle.*
import com.example.stargazers.model.User
import com.example.stargazers.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor
    (
    private val state: SavedStateHandle)
    : ViewModel() {

    // Get selected user data from savedStateHandle, which holds navArgs data by argument name "selectedUser"
    private val _selectedUser = MutableLiveData(state.get<User>("selectedUser")!!)
    val selectedUser: LiveData<User>
        get() = _selectedUser





}



