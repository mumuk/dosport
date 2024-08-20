// Navbar.kt
package com.example.dosport.ui.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Navbar(navController: NavController, onMenuClick: () -> Unit ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                .fillMaxHeight(),
                onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            IconButton(
                modifier = Modifier
                    .fillMaxHeight(),
                onClick = onMenuClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
            // "Next page" функциональность, например, можно заменить на вызов navController.navigate(route)
            // который переходит на конкретный следующий маршрут
            IconButton(
                modifier = Modifier
                    .fillMaxHeight(),
                onClick = {
                val currentRoute = navController.currentDestination?.route
                // Здесь вы можете определить логику перехода на "следующую" страницу
                if (currentRoute == "main_page") {
                    navController.navigate("profile_page")
                }
                // Добавьте другие условия для навигации вперед
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Forward")
            }
            IconButton(
                modifier = Modifier
                    .fillMaxHeight(),
                onClick = { navController.navigate("profile_page") }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            }
        }
    }
}



