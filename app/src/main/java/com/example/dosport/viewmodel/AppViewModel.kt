package com.example.dosport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.dosport.data.model.AppState
import com.example.dosport.data.model.Program
import com.example.dosport.data.model.Screen
import com.example.dosport.data.model.User
import com.example.dosport.data.model.UserState
import createMockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel(
    initialState: AppState = AppState() // Добавляем параметр для начального состояния
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<AppState> = _state.asStateFlow()

    init {
        val (programState, exerciseState, eventState) = createMockData()
        _state.value = _state.value.copy(
            programState = programState,
            exerciseState = exerciseState,
            eventState = eventState
        )
        println("AppState: ${_state.value.programState.selectedProgram}")
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
        println("After login state: ${_state.value.programState.selectedProgram}")
    }

    fun logout() {
        _state.value = _state.value.copy(
            userState = UserState(),
            currentScreen = Screen.Login
        )
    }

    fun selectProgram(
        program: Program,
    ) {

        println("Selected program before handler: ${_state.value.programState.selectedProgram}")
        // Обновляем стейт с выбранной программой
        _state.value = _state.value.copy(
            programState = _state.value.programState.copy(
                selectedProgram = program
            )
        )

        println("--------- 1 state after handler: ${_state.value.programState}")
        println("--------- 2 programs after handler: ${_state.value.programState.programs}")
        println("--------- 3 Selected program after handler: ${_state.value.programState.selectedProgram}")
    }



    fun selectCurrentExerciseIndex(exerciseIndex: Int) {
        _state.value = _state.value.copy(
            exerciseState = _state.value.exerciseState.copy(
                selectedExerciseIndex = exerciseIndex
            )
        )
    }

    fun selectCurrentEventIndex(eventIndex: Int) {
        _state.value = _state.value.copy(
            eventState = _state.value.eventState.copy(
                selectedEventIndex = eventIndex
            )
        )
    }

    fun updateCurrentScreen(screen: Screen) {
        _state.value = _state.value.copy(currentScreen = screen)
    }
}
