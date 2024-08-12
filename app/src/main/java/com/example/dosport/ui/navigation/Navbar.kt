// Navbar.kt
package com.example.dosport.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Navbar(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.navigate("previous_page") }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")

            }
            IconButton(onClick = { navController.navigate("next_page") }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward"
                )

            }
            IconButton(onClick = { navController.navigate("profile_page") }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            }
        }
    }
}