package com.example.dosport.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dosport.data.model.AppState
import com.example.dosport.data.model.Program
import com.example.dosport.data.model.Screen
import com.example.dosport.data.model.User
import com.example.dosport.data.model.UserState
import createMockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()


    init {
        val (programState, exerciseState, eventState) = createMockData()
        _state.value = _state.value.copy(
            programState = programState,
            exerciseState = exerciseState,
            eventState = eventState
        )
        println("AppState: ${_state.value}")
    }

    fun updateUser(user: User) {
        _state.value = _state.value.copy(
            userState = _state.value.userState.copy(
                user = user,
                isLoggedIn = true
            )
        )
    }

    fun login(user: User) {
        println("Before login state: ${_state.value}")
        println("Login--> with user: $user")
        _state.value = _state.value.copy(
            userState = _state.value.userState.copy(
                user = user,
                isLoggedIn = true
            ),
            currentScreen = Screen.Main
        )
        println("After login state: ${_state.value}")
    }


    fun logout() {
        _state.value = _state.value.copy(
            userState = UserState(),
            currentScreen = Screen.Login
        )
    }

    fun selectProgram(program: Program) {
        _state.value = _state.value.copy(
            programState = _state.value.programState.copy(
                selectedProgram = program
            )
        )
    }

    fun updateCurrentScreen(screen: Screen) {
        _state.value = _state.value.copy(currentScreen = screen)
    }
}
