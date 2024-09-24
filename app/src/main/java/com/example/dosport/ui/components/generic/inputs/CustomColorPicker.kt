import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomColorPickerDialog(
    initialColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedColor by remember { mutableStateOf(initialColor) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Select a Color", style = MaterialTheme.typography.titleMedium)
        },
        text = {
            ColorPickerContent(selectedColor = selectedColor, onColorSelect = { color ->
                selectedColor = color
            })
        },
        confirmButton = {
            TextButton(onClick = {
                onColorSelected(selectedColor)
                onDismissRequest()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        },
        shape = MaterialTheme.shapes.medium,
        containerColor = MaterialTheme.colorScheme.background
    )
}


@Composable
fun ColorPickerContent(
    selectedColor: Color,
    onColorSelect: (Color) -> Unit
) {
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.surfaceVariant,
        MaterialTheme.colorScheme.onPrimary,
        MaterialTheme.colorScheme.onSecondary,
        MaterialTheme.colorScheme.onSurface
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Choose a color", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))

        // Сетка цветных блоков
        val columns = 4
        for (i in colors.indices step columns) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (j in 0 until columns) {
                    if (i + j < colors.size) {
                        val color = colors[i + j]
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    color = color,
                                    shape = CircleShape
                                )
                                .border(
                                    BorderStroke(
                                        2.dp,
                                        if (color == selectedColor) MaterialTheme.colorScheme.primary else Color.Transparent
                                    ),
                                    shape = CircleShape
                                )
                                .clickable { onColorSelect(color) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

