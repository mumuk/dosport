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

    private val _isInProcess = MutableStateFlow(false)
    val isInProcess: StateFlow<Boolean> = _isInProcess.asStateFlow()

    init {
        val (programState, exerciseState, eventState) = createMockData()
        _state.value = _state.value.copy(
            programState = programState,
            exerciseState = exerciseState,
            eventState = eventState
        )
        println("0 --------- AppState: ${_state.value.programState.selectedProgram}")
    }

    fun startProgram() {
        selectCurrentExerciseIndex(0)
        selectCurrentEventIndex(0)
        _isInProcess.value = true
    }
    fun resumeProgram() {
        _isInProcess.value = true
    }

    fun pauseProgram() {
        _isInProcess.value = false
    }

    fun endProgram() {
            _isInProcess.value = false
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
        println("1 --------- Login--> with user: $user")



        _state.value = _state.value.copy(
            userState = _state.value.userState.copy(
                user = user,
                isLoggedIn = true
            ),
            currentScreen = Screen.Main
        )
        println("2 --------- Login--> with user: ${_state.value.userState.user}")

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

        println("5 --------- Selected program state before handler: ${_state.value.programState.selectedProgram}")
        // Обновляем стейт с выбранной программой
        _state.value = _state.value.copy(
            programState = _state.value.programState.copy(
                selectedProgram = program
            )
        )

        println("6 --------- Selected program state after handler: ${_state.value.programState.selectedProgram}")

    }


    fun selectCurrentExerciseIndex(exerciseIndex: Int) {
        println("7 --------- Selected exercise state before handler: ${_state.value.exerciseState.selectedExerciseIndex}")
        _state.value = _state.value.copy(
            exerciseState = _state.value.exerciseState.copy(
                selectedExerciseIndex = exerciseIndex
            )
        )
        println("8 --------- Selected exercise state before handler: ${_state.value.exerciseState.selectedExerciseIndex}")
    }

    fun selectCurrentEventIndex(eventIndex: Int) {
        println("9 --------- Selected event state before handler: ${_state.value.eventState.selectedEventIndex}")
        _state.value = _state.value.copy(
            eventState = _state.value.eventState.copy(
                selectedEventIndex = eventIndex
            )
        )
        println("10 --------- Selected event state before handler: ${_state.value.eventState.selectedEventIndex}")
    }

    fun updateCurrentScreen(screen: Screen) {
        _state.value = _state.value.copy(currentScreen = screen)
    }
}
