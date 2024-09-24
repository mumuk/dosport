package com.example.dosport.ui.screens

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dosport.data.model.AppState
import com.example.dosport.data.model.Program
import com.example.dosport.ui.components.programPage.ControlBar
import com.example.dosport.ui.components.programPage.EventDescription
import com.example.dosport.ui.components.programPage.ExerciseDescription
import com.example.dosport.ui.components.programPage.ProgramTitle
import com.example.dosport.ui.components.programPage.ProgressBar
import com.example.dosport.ui.components.programPage.TimeBar
import com.example.dosport.ui.theme.AppTheme
import com.example.dosport.viewmodel.AppViewModel
import createMockData

// TODO: Resume/Pause Button must be inactive after end of the program and must be "Pause" 
@Composable
fun ProgramPage(

    program: Program? = null,
    appViewModel: AppViewModel = viewModel()
) {
    val appState by appViewModel.state.collectAsState()
    val isInProcess by appViewModel.isInProcess.collectAsState()

    if (program != null) {

        // Получаем текущий активный индекс упражнения и события
        val selectedExerciseIndex = appState.exerciseState.selectedExerciseIndex
        val selectedExercise = program.exercises.getOrNull(selectedExerciseIndex)
        println("Index of selected exercise: $selectedExerciseIndex")
        val selectedEventIndex = appState.eventState.selectedEventIndex
        val selectedEvent = selectedExercise?.events?.getOrNull(selectedEventIndex)
        println("Index of selected event: $selectedEventIndex")

        if (selectedExercise != null && selectedEvent != null) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                ) {


                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {


                            // Заголовок программы
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                ProgramTitle(program = program)
                            }


                            // Описание упражнения
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                ExerciseDescription(exercise = selectedExercise)
                            }

                            // Описание события
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                EventDescription(event = selectedEvent)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            // Временная панель (Time Bar)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {


                                TimeBar(
                                    isStarted = isInProcess,
                                    exerciseIndex = selectedExerciseIndex ?: 0,
                                    event = selectedEvent,
                                    currentEventIndex = selectedEventIndex ?: 0,
                                    totalEvents = selectedExercise.events.size,
                                    onEventChange = { newEventIndex ->
                                        appViewModel.selectCurrentEventIndex(newEventIndex)
                                    },
                                    onExerciseFinish = {
                                        val isLastEvent = selectedEventIndex == selectedExercise.events.size - 1
                                        val isLastExercise = selectedExerciseIndex == program.exercises.size - 1

                                        if (isLastEvent && isLastExercise) {
                                            // Если это последнее событие последнего упражнения, завершаем программу
                                            appViewModel.endProgram()
                                            println("Program finished")
                                        } else if (isLastEvent) {
                                            // Если это последнее событие, но упражнение не последнее, переходим к следующему упражнению
                                            appViewModel.selectCurrentEventIndex(0)
                                            appViewModel.selectCurrentExerciseIndex(selectedExerciseIndex + 1)
                                        } else {
                                            println("onExerciseFinish else block")
                                            appViewModel.selectCurrentEventIndex(selectedEventIndex + 1)
                                        }
                                    }
)
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Прогресс бар программы и упражнений
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()

                                .border(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            // Program Progress Bar
                            ProgressBar(

                                exercises = program.exercises,
                                selectedExerciseIndex = selectedExerciseIndex,
                                selectedEventIndex = selectedEventIndex,
                                onExerciseSelected = { index ->
                                    appViewModel.selectCurrentExerciseIndex(exerciseIndex = index)
                                },
                                onEventSelected = { index ->
                                    appViewModel.selectCurrentEventIndex(eventIndex = index)
                                },
                                onResetEventIndex = {
                                    appViewModel.selectCurrentEventIndex(eventIndex = 0)  // Сбрасываем индекс события на 0
                                },


                                )
                        }


                    }
                }
                // Панель управления
                ControlBar(
                    selectedExerciseIndex= selectedExerciseIndex,
                    selectedEventIndex = selectedEventIndex,
                    onStart = { appViewModel.startProgram() },
                    onResume = { appViewModel.resumeProgram() },
                    onPause = { appViewModel.pauseProgram() },
                    onDone = { appViewModel.endProgram() },
                    onEdit = {

                    },
                    isStarted = isInProcess
                )
            }
        } else {
            Text("No Exercise or Event Selected")
        }
    } else {
        // Если программа не выбрана, можно показать сообщение
        Text("No Program Selected")
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light"
)
@Composable
fun ProgramPagePreview() {
    val mockData = createMockData()
    val appState = AppState(
        programState = mockData.first.copy(selectedProgram = mockData.first.programs.first()),
        exerciseState = mockData.second,
        eventState = mockData.third.copy(selectedEventIndex = 0)
    )

    val context = LocalContext.current
    val viewModelFactory = AppViewModelFactory(appState, context)
    val appViewModel: AppViewModel = viewModel(factory = viewModelFactory)

    AppTheme {
        Surface(tonalElevation = 5.dp) {
            ProgramPage(
                appViewModel = appViewModel
            )
        }
    }
}

// Фабрика ViewModel для использования в превью
class AppViewModelFactory(
    private val initialState: AppState,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(initialState) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}