package com.example.dosport.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dosport.data.model.Exercise
import com.example.dosport.data.model.Program
import com.example.dosport.data.model.Status
import com.example.dosport.ui.components.generic.ConfirmModal
import com.example.dosport.ui.components.generic.CustomButton
import com.example.dosport.ui.components.generic.list.GenericList
import com.example.dosport.viewmodel.AppViewModel

// Enum for modal actions (CLEAR or DELETE)
enum class ModalAction {
    CLEAR, DELETE
}

@Composable
fun ProgramEditPage(
    appViewModel: AppViewModel,
    navController: NavController,
    id: String? = null // Nullable ID to check if editing or creating
) {
    val appState by appViewModel.state.collectAsState() // Observing the app's state

    val exercises = appState.exerciseState.exercises // Available exercises from state
    val errorState = appState.errorState // Error state from the app
    var draftProgram = appState.draftProgram ?: Program(name = "New Program")

    // Initialize draft based on whether we're editing or creating a new program
    LaunchedEffect(id) {
        if (id == null) { // Проверка, что это создание новой программы
            println("Creating mode: clear the draft for a new program")
            appViewModel.clearDraftProgram() // Чистим черновик для новой программы
        } else {
            println("Editing mode: set the selected program as the draft")
            appViewModel.state.value.programState.selectedProgram?.let {
                appViewModel.saveDraftProgram(it) // Сохраняем выбранную программу как черновик
            }
        }
    }
    println("draftProgram: $draftProgram")
    var program by remember {
        mutableStateOf(
            appState.draftProgram ?: Program(name = "New Program")
        )
    }

    LaunchedEffect(draftProgram) {
        draftProgram?.let {
            program = it // Update local program when draft changes
        }
    }
    // Observing the draft program


    // Validation function to check if the program is valid
    fun validateProgram() {
        when {
            program.name.isBlank() && program.exercises.isNotEmpty() -> {
                appViewModel.setErrorState(true, "Program name cannot be empty")
            }

            program.name.isBlank() && program.exercises.isEmpty() -> {
                appViewModel.setErrorState(true, "Program must contain at least one exercise")
            }

            else -> {
                appViewModel.clearErrorState() // Clear error if the program is valid
            }
        }
    }

    // State for selecting exercises in program and available exercises
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }
    var selectedProgramExercise by remember { mutableStateOf<Exercise?>(null) }

    // Modal control state
    var showModal by remember { mutableStateOf(false) }
    var modalAction by remember { mutableStateOf(ModalAction.CLEAR) } // Enum to decide modal action

    // Determines if the "Save" button should be enabled
    val isSaveEnabled = program.name.isNotEmpty() && program.exercises.isNotEmpty()

    // Dropdown for status selection
    var expanded by remember { mutableStateOf(false) }
    val statuses = Status.values().map { it.name }

    // ------------------- Functions for actions -------------------

    /** Общий метод для обновления черновика программы */
    fun updateDraftProgram(newProgram: Program) {
        draftProgram = newProgram
        appViewModel.saveDraftProgram(draftProgram)
    }

    /** Adds an exercise to the program's draft */
    fun addExerciseToProgram(exercise: Exercise?) {
        exercise?.let {
            val updatedProgram = program.copy(exercises = program.exercises + exercise)
            updateDraftProgram(updatedProgram) // Обновляем черновик через общий метод
        }
    }

    /** Clears all exercises from the program's draft */
    fun clearProgram() {
        updateDraftProgram(program.copy(exercises = emptyList())) // Обновляем черновик через общий метод
        showModal = false
    }

    /** Deletes the current program */
    fun deleteProgram() {
        appViewModel.deleteProgram(program.id)
        navController.navigate("main_page")
    }

    /** Saves the current program after validation */
    fun saveProgram() {
        if (program.name.isBlank()) {
            appViewModel.setErrorState(true, "Program name cannot be empty")
            return
        }
        if (program.exercises.isEmpty()) {
            appViewModel.setErrorState(true, "Program must contain at least one exercise")
            return
        }
        appViewModel.saveProgramFromDraft() // Save the program from draft state
        navController.navigate("main_page")
    }



    // ------------------- Composable UI -------------------

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header with Delete, Status Dropdown, and Save buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Row with Delete, Status Dropdown, and Save buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // "Delete" button
                        CustomButton(
                            onClick = {
                                modalAction = ModalAction.DELETE
                                showModal = true
                            },
                            enabled = program.id.isNotEmpty(),
                            text = "Delete",
                            modifier = Modifier.width(100.dp),
                            containerColor = MaterialTheme.colorScheme.error, // Red background for active button
                            contentColor = MaterialTheme.colorScheme.onError // White text for button
                        )

                        // Dropdown to change program status
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .padding(horizontal = 8.dp)
                        ) {
                            TextButton(onClick = { expanded = true }) {
                                Text(
                                    text = "Status: ${program.status}",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                statuses.forEach { status ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                status,
                                                color = if (status == program.status) MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.onSurface
                                            )
                                        },
                                        onClick = {
                                            program = program.copy(status = status)
                                            appViewModel.saveDraftProgram(program) // Save status change in draft
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // "Save" button
                        CustomButton(
                            onClick = {
                                validateProgram() // Validate before saving
                                if (!errorState.hasError) {
                                    saveProgram() // Save program only if no errors
                                }
                            },
                            enabled = isSaveEnabled, // Enable only if program is valid
                            text = "Save",
                            modifier = Modifier.width(100.dp)
                        )
                    }

                    // Input for program name
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = draftProgram.name,
                            onValueChange = { newName ->
                                draftProgram = draftProgram.copy(name = newName)
                             // Save name change in draft
                                appViewModel.saveDraftProgram(draftProgram)
                                appViewModel.clearErrorState() // Clear errors on valid input
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Program Name") },
                            isError = errorState.hasError && program.name.isEmpty() // Show error if name is blank
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Display error message if there is one
            errorState.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Program exercises list and management buttons
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    // List of exercises in the program
                    Box(
                        modifier = Modifier
                            .weight(0.67f)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(8.dp)
                    ) {
                        GenericList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            items = program.exercises,
                            selectedItem = selectedProgramExercise,
                            onSelect = { selectedProgramExercise = it }
                        )
                    }

                    // Buttons for managing program exercises
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(0.33f)
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        // "Remove" button to remove selected exercise from program
                        CustomButton(
                            onClick = {
                                selectedProgramExercise?.let {
                                    program = program.copy(exercises = program.exercises - it)
                                    appViewModel.saveDraftProgram(program) // Save removal in draft
                                }
                            },
                            enabled = selectedProgramExercise != null, // Enable only if an exercise is selected
                            text = "Remove",
                            modifier = Modifier.width(100.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // "Clear" button to remove all exercises from program
                        CustomButton(
                            enabled = program.exercises.isNotEmpty(),
                            onClick = {
                                modalAction = ModalAction.CLEAR
                                showModal = true
                            },
                            text = "Clear",
                            modifier = Modifier.width(100.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            // Section for available exercises to add to the program
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Available Exercises",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Button(
                    onClick = { navController.navigate("exercise_edit_page") }, // Navigate to create exercise page
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Create Exercise")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // List of available exercises to add to the program
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(2f / 3f)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(8.dp)
                    ) {
                        GenericList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            items = exercises,
                            selectedItem = selectedExercise,
                            onSelect = { selectedExercise = it }
                        )
                    }

                    // Add exercise button
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f / 3f)
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        CustomButton(
                            onClick = {
                                addExerciseToProgram(selectedExercise)
                            },
                            enabled = selectedExercise != null, // Enable only if an exercise is selected
                            text = "Add",
                            modifier = Modifier.width(100.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

    // Confirm modal for clearing or deleting the program
    if (showModal) {
        ConfirmModal(
            message = "Are you sure you want to $modalAction this program?",
            onConfirm = {
                when (modalAction) {
                    ModalAction.CLEAR -> clearProgram()
                    ModalAction.DELETE -> deleteProgram()
                }
            },
            onCancel = { showModal = false }
        )
    }
}
