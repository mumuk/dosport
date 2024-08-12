package com.example.dosport.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TestRoutingPanel(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("login_page") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "Login Page")
        }
        Button(
            onClick = { navController.navigate("main_page") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "Main Page")
        }
        Button(
            onClick = { navController.navigate("program_edit_page") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "New Program Edit Page")
        }
        Button(
            onClick = { navController.navigate("program_edit_page/1") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "Edit Program Page")
        }
        Button(
            onClick = { navController.navigate("exercise_edit_page") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "New Exercise Edit Page")
        }
        Button(
            onClick = { navController.navigate("exercise_edit_page/1") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "Edit Exercise Page")
        }
        Button(
            onClick = { navController.navigate("event_edit_page") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "New Event Edit Page")
        }
        Button(
            onClick = { navController.navigate("event_edit_page/1") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "Edit Event Page")
        }
        Button(
            onClick = { navController.navigate("screen_edit_page") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "New Screen Edit Page")
        }
        Button(
            onClick = { navController.navigate("screen_edit_page/1") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text(text = "Edit Screen Page")
        }
    }
}
