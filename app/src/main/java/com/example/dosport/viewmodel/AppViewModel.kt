package com.example.dosport.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dosport.data.model.*
import createMockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel(
    initialState: AppState = AppState() // Initial state
) : ViewModel() {

    // State management for the app
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<AppState> = _state.asStateFlow()

    // State to track if a program is in progress
    private val _isInProcess = MutableStateFlow(false)
    val isInProcess: StateFlow<Boolean> = _isInProcess.asStateFlow()

    // ------------------- Draft Program Handling -------------------

    /**
     * Saves the current draft program to state.
     * This is useful for auto-saving changes while editing a program.
     */
    fun saveDraftProgram(program: Program) {
        _state.value = _state.value.copy(draftProgram = program)
    }

    /**
     * Clears the current draft program.
     * Should be called after the program is successfully saved.
     */
    fun clearDraftProgram() {

        _state.value = _state.value.copy(draftProgram = null)
        println("clearDraftProgram called with draftProgram: ${_state.value.draftProgram}")
    }

    /**
     * Saves the current draft program into the list of saved programs.
     * Validates the program before saving.
     */
    fun saveProgramFromDraft() {
        val draftProgram = _state.value.draftProgram
        draftProgram?.let {
            // Check for errors before saving
            if (it.name.isBlank()) {
                setErrorState(true, "Program name cannot be empty")
                return
            }
            if (it.exercises.isEmpty()) {
                setErrorState(true, "Program must contain at least one exercise")
                return
            }

            // Save program in the list of programs
            val updatedPrograms = _state.value.programState.programs.toMutableList().apply {
                val index = indexOfFirst { program -> program.id == draftProgram.id }
                if (index != -1) {
                    set(index, draftProgram) // Update existing program
                } else {
                    add(draftProgram) // Add new program
                }
            }

            // Update state with the saved program
            _state.value = _state.value.copy(
                programState = _state.value.programState.copy(
                    programs = updatedPrograms,
                    selectedProgram = draftProgram
                )
            )

            // Clear the draft after successful save
            clearDraftProgram()
            clearErrorState()

        } ?: setErrorState(true, "No draft program to save")
    }

    /**
     * Updates the current draft program with the given changes.
     * This method auto-saves changes as a draft.
     */
    fun updateDraftProgram(newProgram: Program) {
        saveDraftProgram(newProgram)
    }

    // ------------------- Error State Handling -------------------

    /**
     * Sets an error state with a custom message.
     * @param hasError Boolean indicating if there's an error.
     * @param errorMessage Optional error message.
     */
    fun setErrorState(hasError: Boolean, errorMessage: String? = null) {
        _state.value = _state.value.copy(
            errorState = _state.value.errorState.copy(
                hasError = hasError,
                errorMessage = errorMessage
            )
        )
    }

    /**
     * Clears the current error state.
     */
    fun clearErrorState() {
        setErrorState(false, null)
    }

    // ------------------- Program Management -------------------

    /**
     * Selects a program to be edited or displayed.
     */
    fun selectProgram(program: Program) {
        _state.value = _state.value.copy(
            programState = _state.value.programState.copy(
                selectedProgram = program
            )
        )
        clearErrorState() // Clear any previous errors related to program selection
    }

    /**
     * Deletes a program by its ID.
     */
    fun deleteProgram(id: String) {
        val updatedPrograms = _state.value.programState.programs.filterNot { it.id == id }
        _state.value = _state.value.copy(
            programState = _state.value.programState.copy(
                programs = updatedPrograms
            )
        )
    }

    // ------------------- Exercise Management -------------------

    /**
     * Adds an exercise to the draft program.
     * Ensures that the exercise is added to the current draft for easy editing.
     */
    fun addExerciseToDraftProgram(exercise: Exercise) {
        val draftProgram = _state.value.draftProgram
        draftProgram?.let {
            val updatedProgram = it.copy(exercises = it.exercises + exercise)
            saveDraftProgram(updatedProgram)
        } ?: setErrorState(true, "No draft program available to add exercise")
    }

    // ------------------- User Management -------------------

    /**
     * Logs in the user and updates the available exercises.
     */
    fun login(user: User) {
        try {
            _state.value = _state.value.copy(
                userState = _state.value.userState.copy(
                    user = user,
                    isLoggedIn = true
                ),
                currentScreen = Screen.Main
            )
            _state.value = _state.value.copy(
                exerciseState = _state.value.exerciseState.copy(
                    exercises = getAvailableExercisesForUser(user)
                )
            )
            clearErrorState() // Clear any previous login errors
        } catch (e: Exception) {
            setErrorState(true, "Login failed: ${e.message}")
        }
    }

    /**
     * Logs out the current user and resets relevant state.
     */
    fun logout() {
        _state.value = _state.value.copy(
            userState = _state.value.userState.copy(
                user = null,
                isLoggedIn = false
            ),
            currentScreen = Screen.Login
        )
    }

    /**
     * Fetches the available exercises for the user, filtering by access level (public, friends, created by the user).
     */
    private fun getAvailableExercisesForUser(user: User?): List<Exercise> {
        return user?.let {
            mockExercises.filter { exercise ->
                exercise.status == Status.PUBLIC.name ||
                        exercise.createdBy == user.id ||
                        (exercise.status == Status.FRIENDS.name && user.friendsIds.contains(exercise.createdBy))
            }
        } ?: emptyList()
    }

    // ------------------- Program Execution Management -------------------

    /**
     * Starts a program, resetting the exercise and event indices.
     */
    fun startProgram() {
        selectCurrentExerciseIndex(0)
        selectCurrentEventIndex(0)
        _isInProcess.value = true
    }

    /**
     * Resumes a paused program.
     */
    fun resumeProgram() {
        _isInProcess.value = true
    }

    /**
     * Pauses the current program.
     */
    fun pauseProgram() {
        _isInProcess.value = false
    }

    /**
     * Ends the current program execution.
     */
    fun endProgram() {
        _isInProcess.value = false
    }

    // ------------------- Screen Management -------------------

    /**
     * Updates the current screen in the app.
     */
    fun updateCurrentScreen(screen: Screen) {
        _state.value = _state.value.copy(currentScreen = screen)
    }

    // ------------------- Exercise and Event Selection -------------------

    /**
     * Selects the current exercise by its index.
     */
    fun selectCurrentExerciseIndex(exerciseIndex: Int) {
        _state.value = _state.value.copy(
            exerciseState = _state.value.exerciseState.copy(
                selectedExerciseIndex = exerciseIndex
            )
        )
    }

    /**
     * Selects the current event by its index.
     */
    fun selectCurrentEventIndex(eventIndex: Int) {
        _state.value = _state.value.copy(
            eventState = _state.value.eventState.copy(
                selectedEventIndex = eventIndex
            )
        )
    }

    // ------------------- Initialization -------------------

    init {
        // Initialize the state with mock data on ViewModel creation
        val (programState, exerciseState, eventState) = createMockData()
        _state.value = _state.value.copy(
            programState = programState,
            exerciseState = exerciseState,
            eventState = eventState
        )
    }
}
