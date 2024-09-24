package com.example.dosport.ui.components.programPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.dosport.data.model.Exercise
import kotlinx.coroutines.delay
import com.example.dosport.data.model.Event


@Composable
fun Timer(
    timeLeft: Long,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Time left: ${timeLeft / 1000}s",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = Color.Black,
        modifier = modifier
    )
}


@Composable
fun TimeBar(
    isStarted: Boolean,
    exerciseIndex: Int,
    event: Event,
    currentEventIndex: Int,
    totalEvents: Int,
    onEventChange: (Int) -> Unit,
    onExerciseFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    var timeLeft by remember { mutableStateOf(event.duration) }

    LaunchedEffect(exerciseIndex, event) {
        println(" Time Bar - ExerciseIndex: $exerciseIndex")
        println("Time Bar - Event: $event")
        timeLeft = event.duration // Сбрасываем таймер при переключении события
    }

//    println("isStarted: $isStarted")

    LaunchedEffect(timeLeft, isStarted) {
        if (isStarted) {
            if (timeLeft > 0) {
                delay(1000L)
                timeLeft -= 1000L
            } else {


                if (currentEventIndex < totalEvents - 1) {
                    onEventChange(currentEventIndex + 1) // Переключаемся на следующее событие
                } else {
                    onExerciseFinish() // Завершаем упражнение
                }
            }
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Timer(
            timeLeft = timeLeft,
        )
    }
}



