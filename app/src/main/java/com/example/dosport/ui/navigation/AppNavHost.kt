package com.example.dosport.ui.navigation


import com.example.dosport.ui.screens.ProfilePage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dosport.R
import com.example.dosport.ui.screens.EventEditPage
import com.example.dosport.ui.screens.ExerciseEditPage
import com.example.dosport.ui.screens.LoginPage
import com.example.dosport.ui.screens.MainPage
import com.example.dosport.ui.screens.Program
import com.example.dosport.ui.screens.ProgramEditPage
import com.example.dosport.ui.screens.ProgramPage
import com.example.dosport.ui.screens.ScreenEditPage
import com.example.dosport.viewmodel.AuthViewModel


@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel() // Подключаем ViewModel для отслеживания авторизации
) {
    val userIsLoggedIn by authViewModel.userIsLoggedIn

    // Следим за состоянием авторизации и перенаправляем на соответствующую страницу
    LaunchedEffect(userIsLoggedIn) {
        if (userIsLoggedIn) {
            navController.navigate("main_page") {
                popUpTo("login_page") { inclusive = true } // Удаляем LoginPage из стека
            }
        } else {
            navController.navigate("login_page") {
                popUpTo("main_page") { inclusive = true } // Удаляем MainPage из стека
            }
        }
    }

    NavHost(navController, startDestination = "login_page") { // Начальная страница — LoginPage
        composable("login_page") {
            LoginPage(
                navController = navController,
                onLoginSuccess = {
                    authViewModel.logIn() // Устанавливаем состояние авторизации как true при успешном входе
                },
                onRegister = {
                    authViewModel.logIn() // Устанавливаем состояние авторизации как true при успешной регистрации
                navController.navigate("main_page"){
                    popUpTo("login_page") { inclusive = true }
                }
                }
            )
        }
        composable("main_page") {
            MainPage(
                navController = navController,
                programs = listOf(
                    Program(1, "Morning Exercise", "A set of exercises to start your day."),
                    Program(2, "Evening Stretching", "Relaxing stretches to end the day.")
                ),
                onEdit = {},
                onStart = {},
                onCreate = { navController.navigate("program_edit_page") },
                onProfile = { navController.navigate("profile_page") },
                onCalendar = {},
            )
        }
        composable("profile_page") {
            ProfilePage(
                profilePicture = painterResource(id = R.drawable.ic_profile_placeholder),
                userName = "John Doe",
                userEmail = "johndoe@example.com",
                onNameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onAvatarChange = {},
                onLanguageChange = {},
                onLogout = {
                    authViewModel.logOut() // Устанавливаем состояние авторизации как false при выходе из системы
                },
                navController = navController
            )
        }
        composable("program_page/{id}") { backStackEntry ->
            ProgramPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
        composable("program_edit_page") { ProgramEditPage(navController = navController) }
        composable("program_edit_page/{id}") { backStackEntry ->
            ProgramEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
        composable("exercise_edit_page") { ExerciseEditPage(navController = navController) }
        composable("exercise_edit_page/{id}") { backStackEntry ->
            ExerciseEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
        composable("event_edit_page") {
            EventEditPage(
                navController = navController,
                onSave = { event ->
                    // Логика сохранения события
                    navController.popBackStack() // Возвращаемся на предыдущий экран после сохранения
                },
                onCancel = {
                    navController.popBackStack() // Возвращаемся на предыдущий экран при отмене
                }
            )
        }
        composable("event_edit_page/{id}") { backStackEntry ->
            EventEditPage(
                navController = navController,
                id = backStackEntry.arguments?.getString("id"),
                onSave = { event ->
                    // Логика сохранения события
                    navController.popBackStack() // Возвращаемся на предыдущий экран после сохранения
                },
                onCancel = {
                    navController.popBackStack() // Возвращаемся на предыдущий экран при отмене
                }
            )
        }
        composable("screen_edit_page") { ScreenEditPage(navController = navController) }
        composable("screen_edit_page/{id}") { backStackEntry ->
            ScreenEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
    }
}