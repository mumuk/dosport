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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dosport.viewmodel.NavigationViewModel

@Composable
fun Navbar(
    navController: NavController,
    onMenuClick: () -> Unit,
    navigationViewModel: NavigationViewModel = viewModel()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // Добавляем текущий маршрут в историю навигации
    LaunchedEffect(currentRoute) {
        currentRoute?.let {
            if (navigationViewModel.backStack.lastOrNull() != it) {
                navigationViewModel.navigateTo(it)
            }
        }
    }

    val canNavigateBack = navigationViewModel.canNavigateBack()
    val canNavigateForward = navigationViewModel.canNavigateForward()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = {
                    if (canNavigateBack) {
                        val previousRoute = navigationViewModel.popBackStack()
                        previousRoute?.let { navController.navigate(it) }
                    }
                },
                enabled = canNavigateBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = if (canNavigateBack) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            }
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = onMenuClick
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = {
                    if (canNavigateForward) {
                        val nextRoute = navigationViewModel.navigateForward()
                        nextRoute?.let { navController.navigate(it) }
                    }
                },
                enabled = canNavigateForward
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward",
                    tint = if (canNavigateForward) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            }
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = {
                    val profileRoute = "profile_page"
                    navController.navigate(profileRoute)
                }
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            }
        }
    }
}






