package com.example.dosport.ui.navigation


import com.example.dosport.ui.screens.ProfilePage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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

import com.example.dosport.ui.screens.ProgramEditPage
import com.example.dosport.ui.screens.ProgramPage
import com.example.dosport.ui.screens.ScreenEditPage
import com.example.dosport.viewmodel.AppViewModel


@Composable
fun AppNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel = viewModel()
) {
    val appState by appViewModel.state.collectAsState()




    NavHost(navController, startDestination = "login_page") {
        composable("login_page") {
            LoginPage(
                navController = navController,
                onLoginSuccess = { user ->
                    appViewModel.login(user)
                },
                onRegister = { user ->
                    appViewModel.login(user) // Устанавливаем состояние авторизации как true при успешной регистрации
                    navController.navigate("main_page") {
                        popUpTo("login_page") { inclusive = true }
                    }
                }
            )
        }
        composable("main_page") {
            MainPage(
                appViewModel = appViewModel,
                navController = navController,
                programs = appState.programState.programs,
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
                userName = "${appState.userState.user?.firstName} ${appState.userState.user?.lastName}",
                userEmail = appState.userState.user?.email ?: "",
                onNameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onAvatarChange = {},
                onLanguageChange = {},
                onSave = {
                    navController.navigate("main_page")
                },
                onLogout = {
                    appViewModel.logout() // Устанавливаем состояние авторизации как false при выходе из системы
                },
                navController = navController
            )
        }
        composable("program_page/{id}") { backStackEntry ->
            val programId = backStackEntry.arguments?.getString("id")
            val program = appState.programState.programs.find { it.id == programId }
            if (program != null) {
                ProgramPage(navController = navController)
            }
        }
        composable("program_edit_page") { ProgramEditPage(navController = navController) }
        composable("program_edit_page/{id}") { backStackEntry ->
            ProgramEditPage(
                navController = navController,
                id = backStackEntry.arguments?.getString("id")
            )
        }
        composable("exercise_edit_page") { ExerciseEditPage(navController = navController) }
        composable("exercise_edit_page/{id}") { backStackEntry ->

            val exerciseId = backStackEntry.arguments?.getString("id")
            val exercise = appState.exerciseState.exercises.find { it.id == exerciseId }
            if (exercise != null) {
                ExerciseEditPage(
                    navController = navController,
                    exercise = exercise
                )
            }
        }
        composable("event_edit_page") {
            EventEditPage(
                navController = navController,
                onSave = {
                    // Логика сохранения события
                    navController.popBackStack() // Возвращаемся на предыдущий экран после сохранения
                },
                onCancel = {
                    navController.popBackStack() // Возвращаемся на предыдущий экран при отмене
                }
            )
        }
        composable("event_edit_page/{id}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("id")
            val event = appState.eventState.events.find { it.id == eventId }
            EventEditPage(
                navController = navController,
                event = event,
                onSave = {
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
            ScreenEditPage(
                navController = navController,
                id = backStackEntry.arguments?.getString("id")
            )
        }
    }
    //Следим за состоянием авторизации и перенаправляем на соответствующую страницу
    LaunchedEffect(appState.userState.isLoggedIn) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (appState.userState.isLoggedIn && currentRoute != "main_page") {
            navController.navigate("main_page") {
                popUpTo("login_page") { inclusive = true } // Удаляем LoginPage из стека
            }
        } else if (!appState.userState.isLoggedIn && currentRoute != "login_page") {
            navController.navigate("login_page") {
                popUpTo("main_page") { inclusive = true } // Удаляем MainPage из стека
            }
        }
    }
}