package com.example.stargazers.ui.mainpage

import androidx.lifecycle.*
import com.example.stargazers.model.User
import com.example.stargazers.repository.MainRepository
import com.example.stargazers.ui.userspage.UsersViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainpageViewModel
@Inject
constructor(
mainRepository: MainRepository
) : ViewModel() {





}