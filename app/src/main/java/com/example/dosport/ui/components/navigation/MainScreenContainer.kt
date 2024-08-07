package com.example.dosport.ui.components.navigation

import ProfilePage
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dosport.R
import com.example.dosport.ui.components.EventEditPage
import com.example.dosport.ui.components.ExerciseEditPage
import com.example.dosport.ui.components.LoginPage
import com.example.dosport.ui.components.MainPage
import com.example.dosport.ui.components.Program
import com.example.dosport.ui.components.ProgramEditPage
import com.example.dosport.ui.components.ScreenEditPage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContainer(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = { Navbar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                AppNavHost(navController = navController)
            }
//            TestRoutingPanel(navController = navController)
        }
    }
}
