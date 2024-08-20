package com.example.dosport.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dosport.data.model.Exercise
import com.example.dosport.ui.theme.AppTheme

@Composable
fun ExerciseEditPage(navController: NavController,exercise: Exercise? = null) {


    Surface(color = MaterialTheme.colorScheme.background) {
        if (exercise != null) {
            Text(text = "Editing Exercise: ${exercise.name}")
            // Добавьте элементы редактирования упражнения здесь
        } else {
            Text(text = "Exercise not found")
        }
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
fun ExerciseEditPagePreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp) {
            ExerciseEditPage(navController = rememberNavController(), exercise = Exercise(id = "1", name = "Exercise 1", description = "Description 1"))
        }
    }
}
