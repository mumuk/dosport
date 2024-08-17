package com.example.dosport.ui.screens

import CustomColorPickerDialog
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dosport.data.model.Event
import com.example.dosport.ui.components.inputs.CustomTextInput
import com.example.dosport.ui.theme.AppTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventEditPage(
    navController: NavController,
    id: String? = null,
    onSave: (Event) -> Unit,
    onCancel: () -> Unit
) {

    val event = id?.let {loadEventById(it) }
    val initialColor = event?.color ?: MaterialTheme.colorScheme.primary
    var eventName by remember { mutableStateOf(event?.name ?: "") }
    var eventDescription by remember { mutableStateOf(event?.description ?: "") }
    var eventColor by remember { mutableStateOf(initialColor) }
    var isColorPickerVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (event == null) "Create Event" else "Edit Event") },
                actions = {
                    IconButton(onClick = {
                        val newEvent = Event(
                            id = event?.id ?: UUID.randomUUID().toString(),
                            name = eventName,
                            description = eventDescription,
                            color = eventColor.toString()
                        )
                        onSave(newEvent)
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Save")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextInput(
                    value = eventName,
                    onValueChange = { eventName = it },
                    label = "Event Name"
                )
                CustomTextInput(
                    value = eventDescription,
                    onValueChange = { eventDescription = it },
                    label = "Event Description",
                    isSingleLine = false
                )
                TextButton(onClick = { isColorPickerVisible = true }) {
                    Text("Select Event Color")
                }
                if (isColorPickerVisible) {
                    CustomColorPickerDialog(
                        initialColor = MaterialTheme.colorScheme.primary,
                        onColorSelected = { selectedColor ->
                            eventColor = selectedColor
                        },
                        onDismissRequest = { isColorPickerVisible = false }
                    )
                }
            }
        }
    )
}


@Composable
fun loadEventById(id: String): Event? {
    // Здесь должна быть логика загрузки события из базы данных, API и т.д.
    // Например:
    return Event(id, "Sample Event", "This is a sample event description", 10, MaterialTheme.colorScheme.primary.toString())
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
            EventEditPage(navController = rememberNavController(), id = "123", onSave = {}, onCancel = {})
        }
    }
}
