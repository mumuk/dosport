package com.example.dosport.ui.components.programPage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ControlBar(
    isStarted: Boolean,             // Состояние, показывающее, начат ли процесс
    onStart: () -> Unit,            // Callback нажатия кнопки Start
    onPause: () -> Unit,            // Callback нажатия кнопки Pause
    onDone: () -> Unit,             // Callback нажатия кнопки Done
    onEdit: () -> Unit,             // Callback нажатия кнопки Edit
) {
    // Задаем общие параметры для всех кнопок
    val buttonWidth = 90.dp
    val buttonTextSize = 16.sp
    val horizontalPadding = 8.dp
    val verticalPadding = 4.dp
    val textModifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Кнопка Start для старта процесса с самого начала упражнения
        Button(
            onClick = onStart,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding),
            modifier = Modifier.width(buttonWidth)
        ) {
            Text(text = "Start", style = MaterialTheme.typography.labelLarge.copy(fontSize = buttonTextSize))
        }

        // Кнопка Resume / Pause
        if (!isStarted) {
            Button(
                onClick = onStart,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding),
                modifier = Modifier.width(buttonWidth)
            ) {
                Text(
                    text = "Resume",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = buttonTextSize),
                    modifier = textModifier
                )
            }
        } else {
            Button(
                onClick = onPause,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding),
                modifier = Modifier.width(buttonWidth)
            ) {
                Text(
                    text = "Pause",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = buttonTextSize),
                    modifier = textModifier
                )
            }
        }

        // Кнопка Done
        Button(
            onClick = onDone,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding),
            modifier = Modifier.width(buttonWidth)
        ) {
            Text(
                text = "Done",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = buttonTextSize),
                modifier = textModifier
            )
        }

        // Кнопка Edit
        Button(
            onClick = onEdit,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.width(buttonWidth)
        ) {
            Text(text = "Edit",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = buttonTextSize),
                modifier = textModifier
            )
        }
    }
}



