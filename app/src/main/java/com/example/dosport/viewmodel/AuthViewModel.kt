package com.example.dosport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class AuthViewModel : ViewModel() {

    private val _userIsLoggedIn = mutableStateOf(false)
    val userIsLoggedIn: State<Boolean> = _userIsLoggedIn


    fun logIn() {
        _userIsLoggedIn.value = true
    }

    fun logOut() {
        _userIsLoggedIn.value = false
    }
}