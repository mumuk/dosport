package com.example.dosport.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController



import com.example.dosport.viewmodel.AppViewModel

@Composable
fun MainScreenContainer(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var isMenuVisible by remember { mutableStateOf(false) }
    val appState by appViewModel.state.collectAsState()
    val userIsLoggedIn = appState.userState.isLoggedIn

    // Закрываем меню при смене маршрута
    LaunchedEffect(currentRoute) {
        isMenuVisible = false
    }


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

    Scaffold(
        bottomBar = {
            if (userIsLoggedIn && navController.currentDestination?.route != "login_page") {
                Navbar(
                    navController = navController,
                    onMenuClick = { isMenuVisible = !isMenuVisible },
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Основной контент приложения
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    AppNavHost(navController = navController,appViewModel = appViewModel)
                }
            }

            // Панель навигации как модальное окно
            if (isMenuVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 96.dp) // Отступ для Navbar
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    TestRoutingPanel(navController = navController, onMenuClose = { isMenuVisible = false })
                }
//                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }

}
