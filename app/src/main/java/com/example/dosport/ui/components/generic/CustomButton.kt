package com.example.dosport.ui.components.generic

import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp



import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    enabled: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.primary, // Возможность задавать цвет кнопки
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,  // Возможность задавать цвет текста
    textStyle: TextStyle = MaterialTheme.typography.labelLarge  // Стиль текста
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,


        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) containerColor else Color.Transparent,
            contentColor = if (enabled) contentColor else containerColor
        )
    ) {
        Text(text, style = textStyle)
    }
}
