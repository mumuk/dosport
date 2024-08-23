package com.example.dosport.ui.components.programPage


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.dosport.data.model.Exercise


@Composable
fun ProgressBar(
    exercises: List<Exercise>,
    selectedExerciseIndex: Int,
    selectedEventIndex: Int?,
    onExerciseSelected: (Int) -> Unit,
    onEventSelected: (Int) -> Unit,
    onResetEventIndex: () -> Unit  // Добавлен новый коллбэк для сброса индекса события
) {
    // Переменные для высот
    val programBarHeight = 20.dp
    val selectedExerciseHeight = 24.dp
    val unselectedExerciseHeight = 10.dp
    val spacerHeight = 12.dp
    val selectedEventHeight = 24.dp
    val unselectedEventHeight = 10.dp

    Column(
        modifier = Modifier.height(170.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Program Progress Bar
        val totalProgramDuration = exercises.sumOf { it.duration }
        Spacer(modifier = Modifier.height(spacerHeight))
        Text(
            "select exercise",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 18.sp,
            fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
            color = MaterialTheme.colorScheme.primary

            )


        Row(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .height(programBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            exercises.forEachIndexed { exerciseIndex, exercise ->
                val exerciseWidthFraction =
                    exercise.duration.toFloat() / totalProgramDuration.toFloat()
                val isSelectedExercise = exerciseIndex == selectedExerciseIndex

                Box(
                    modifier = Modifier
                        .weight(exerciseWidthFraction)
                        .height(if (isSelectedExercise) selectedExerciseHeight else unselectedExerciseHeight)
                        .border(
                            width = if (isSelectedExercise) 2.dp else 1.dp,  // Условная логика для ширины границы
                            color = MaterialTheme.colorScheme.primary
                        )
                        .background(Color(android.graphics.Color.parseColor(exercise.color)))
                        .clickable {
                            onExerciseSelected(exerciseIndex)
                            onResetEventIndex()  // Сбрасываем selectedEventIndex на 0 при выборе нового упражнения
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(spacerHeight))

        // Exercise Progress Bar
        val selectedExercise = exercises.getOrNull(selectedExerciseIndex)
        selectedExercise?.let { exercise ->
            val totalExerciseDuration = exercise.events.sumOf { it.duration }
            Text(
                "select movement",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .height(programBarHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {
                exercise.events.forEachIndexed { eventIndex, event ->
                    val eventWidthFraction =
                        event.duration.toFloat() / totalExerciseDuration.toFloat()
                    val isSelectedEvent = eventIndex == selectedEventIndex

                    Box(
                        modifier = Modifier
                            .weight(eventWidthFraction)
                            .height(if (isSelectedEvent) selectedEventHeight else unselectedEventHeight)
                            .border(
                                width = if (isSelectedEvent) 2.dp else 1.dp,  // Условная логика для ширины границы
                                color = MaterialTheme.colorScheme.primary
                            )
                            .background(Color(android.graphics.Color.parseColor(event.color)))
                            .clickable { onEventSelected(eventIndex) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(spacerHeight))
        }
    }
}
