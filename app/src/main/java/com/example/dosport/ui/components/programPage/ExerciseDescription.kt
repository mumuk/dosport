package com.example.dosport.ui.components.programPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dosport.data.model.Exercise


fun parseColor(hexColor: String): Color {
    return Color(android.graphics.Color.parseColor(hexColor))
}

@Composable
fun ExerciseDescription(exercise: Exercise) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

//            .background(parseColor(exercise.color))// Фон окрашен в цвет упражнения

            .border(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = exercise.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary, // Текст белый или черный в зависимости от темы
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = exercise.description,
            style = MaterialTheme.typography.headlineSmall,
            color = parseColor("#FF5733"), // Цвет текста соответствует цвету упражнения
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}

