package com.example.dosport.data.model


sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Profile : Screen("profile")
    object ProgramEdit : Screen("program_edit")
    object ExerciseEdit : Screen("exercise_edit")
    object EventEdit : Screen("event_edit")
    object Login : Screen("login")

    // Добавьте другие экраны вашего приложения
}

data class ScreenState(
    val currentScreen: Screen = Screen.Login // Main - это пример экрана, который будет стартовым
)