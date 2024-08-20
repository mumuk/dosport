package com.example.dosport.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dosport.ui.theme.AppTheme
import androidx.navigation.NavBackStackEntry
import com.example.dosport.data.model.Program


@Composable
fun ProgramPage(navController: NavController, program: Program) {

    Surface(color = MaterialTheme.colorScheme.background) {
        Text(text = "Program Page with ID: ${program.id}")
        Text(text = "Program Name: ${program.name}")
        Text(text = "Program Description: ${program.description}")
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light"
)
@Composable
fun ProgramPagePreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp) {
            ProgramPage(navController = rememberNavController(), program = Program(id = "1", name = "Program 1", description = "Description 1"))
        }
    }
}
