package com.example.dosport.ui.components.programPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dosport.data.model.Event

@Composable
fun EventDescription(event: Event) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
//            .background(parseColor(event.color)) // Фон окрашен в цвет события
            .padding(16.dp)
    ) {
        Text(
            text = event.name,
            style = MaterialTheme.typography.headlineSmall,
//            color = MaterialTheme.colorScheme.onPrimary, // Текст белый или черный в зависимости от темы
            color = MaterialTheme.colorScheme.primary, // Текст белый или черный в зависимости от темы
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (event.description.isNotEmpty()) {
            Text(
                text = event.description,
                style = MaterialTheme.typography.headlineSmall,
                color = parseColor("#FF5733"), // Цвет текста соответствует цвету события
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


