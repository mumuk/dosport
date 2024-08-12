package com.example.dosport.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dosport.ui.components.navigation.Navbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContainer(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(

        bottomBar = {
            if (currentRoute != "login_page") {
                Navbar(navController)
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                AppNavHost(navController = navController)
            }
            TestRoutingPanel(navController = navController)
        }
    }
}
