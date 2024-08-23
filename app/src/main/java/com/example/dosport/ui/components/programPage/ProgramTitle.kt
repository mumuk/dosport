package com.example.dosport.ui.components.programPage



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dosport.data.model.Exercise
import androidx.compose.ui.Alignment
import com.example.dosport.data.model.Program

@Composable
fun ProgramTitle(
    program: Program,
    modifier: Modifier = Modifier
) {
    // Получаем цвет текста из Exercise.color, если он не задан, используем черный по умолчанию

    Row(modifier = Modifier
        .fillMaxWidth()
    ){
    Text(
        text = program.name,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    )
        }
}
