package com.example.dosport.data.model


sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Profile : Screen("profile")
    data object ProgramEdit : Screen("program_edit")
    data object ExerciseEdit : Screen("exercise_edit")
    data object EventEdit : Screen("event_edit")
    data object Login : Screen("login")

    // Добавьте другие экраны вашего приложения
}

data class ScreenState(
    val currentScreen: Screen = Screen.Login // Main - это пример экрана, который будет стартовым
)