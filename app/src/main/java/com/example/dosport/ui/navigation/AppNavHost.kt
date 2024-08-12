package com.example.dosport.ui.navigation


import com.example.dosport.ui.screens.ProfilePage
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
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
import com.example.dosport.ui.screens.ScreenEditPage


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "login_page") {
        composable("login_page") { LoginPage(navController) }
        composable("main_page") {
            MainPage(
                navController,
                programs = listOf(
                    Program(1, "Morning Exercise", "A set of exercises to start your day."),
                    Program(2, "Evening Stretching", "Relaxing stretches to end the day.")
                ),
                onEdit = {},
                onStart = {},
                onCreate = {navController.navigate("program_edit_page")},
                onProfile = {},
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
                onLogout = {},
                navController = navController
            )
        }
        composable("program_edit_page") { ProgramEditPage(navController = navController) }
        composable("program_edit_page/{id}") { backStackEntry ->
            ProgramEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
        composable("exercise_edit_page") { ExerciseEditPage(navController = navController) }
        composable("exercise_edit_page/{id}") { backStackEntry ->
            ExerciseEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
        composable("event_edit_page") { EventEditPage(navController = navController) }
        composable("event_edit_page/{id}") { backStackEntry ->
            EventEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
        composable("screen_edit_page") { ScreenEditPage(navController = navController) }
        composable("screen_edit_page/{id}") { backStackEntry ->
            ScreenEditPage(navController = navController, id = backStackEntry.arguments?.getString("id"))
        }
    }
}