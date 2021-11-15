package com.example.stargazers.ui.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.stargazers.model.User
import com.example.stargazers.repository.MainRepository
import com.example.stargazers.ui.userspage.UsersViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MainpageViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository
) : ViewModel() {








}