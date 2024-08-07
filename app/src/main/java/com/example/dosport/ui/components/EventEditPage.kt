package com.example.dosport.ui.components

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

@Composable
fun EventEditPage(navController: NavController, id: String? = null) {
    Surface(color = MaterialTheme.colorScheme.background) {
        if (id == null) {
            Text(text = "Event Edit Page - New")
        } else {
            Text(text = "Event Edit Page - Editing $id")
        }
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
fun EventEditPagePreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp) {
            EventEditPage(navController = rememberNavController(), id = "123")
        }
    }
}
