package com.example.stargazers.ui.mainpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.stargazers.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainpageViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository
) : ViewModel() {



}