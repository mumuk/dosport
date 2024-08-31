package com.example.dosport.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.dosport.data.model.Program
import com.example.dosport.ui.theme.AppTheme
import com.example.dosport.viewmodel.AppViewModel


@Composable
fun MainPage(
    appViewModel: AppViewModel,
    navController: NavController,
    programs: List<Program> = appViewModel.state.collectAsState().value.programState.programs,
    onEdit: (Program) -> Unit = { program ->
        println("onEdit called with program: $program")

        appViewModel.selectProgram(
            program
        )
    },
    onStart: (Program) -> Unit = { program ->
        println("onStart called with program: $program")
        appViewModel
            .selectProgram(program)
    },
    onCreate: () -> Unit = {
        navController.navigate("program_create_page")
    },
    onProfile: () -> Unit,
    onCalendar: () -> Unit
) {
    var selectedProgram by remember { mutableStateOf<Program?>(null) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
//            Row(
//                horizontalArrangement = Arrangement.End,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Button(
//                    onClick = onProfile,
//                    modifier = Modifier.width(120.dp)
//                ) {
//                    Text("Profile")
//                }
//            }
//            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Programs List",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(2f / 3f)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .shadow(1.dp, RectangleShape, clip = false)
                            .padding(8.dp)

                    ) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(programs) { program ->
                                ProgramItem(
                                    program = program,
                                    isSelected = program == selectedProgram,
                                    onSelect = { selectedProgram = it }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f / 3f)
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        CustomButton(
                            onClick = {
                                selectedProgram?.let {
//
                                    appViewModel.selectProgram(program = it)
                                    navController.navigate("program_page/${selectedProgram?.id}")
                                }
                            },
                            enabled = selectedProgram != null,
                            text = "Start"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomButton(
                            onClick = {
                                selectedProgram?.let {
                                    appViewModel.selectProgram(program = it)
                                    navController.navigate("program_edit_page/${selectedProgram?.id}")
                                }
                            },
                            enabled = selectedProgram != null,
                            text = "Edit"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onCreate,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
            ) {
                Text("Create Program")
            }
        }
    }
}

@Composable
fun CustomButton(onClick: () -> Unit, enabled: Boolean, text: String) {
    val shape: Shape = MaterialTheme.shapes.medium

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!enabled) Modifier.border(
                    1.dp,
                    MaterialTheme.colorScheme.primary,
                    shape
                ) else Modifier
            ),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) MaterialTheme.colorScheme.primary else Color.Transparent,
            contentColor = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text)
    }
}

@Composable
fun ProgramItem(program: Program, isSelected: Boolean, onSelect: (Program) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(program) }
            .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent)
            .padding(8.dp)
    ) {
        Text(
            text = program.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
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
fun MainPagePreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp, modifier = Modifier) {
            val samplePrograms = listOf(
                Program("1", "Morning Exercise", "A set of exercises to start your day."),
                Program("2", "Evening Stretching", "Relaxing stretches to end the day.")
            )
            MainPage(
                programs = samplePrograms,
                onEdit = {},
                onStart = {},
                onCreate = {},
                onProfile = {},
                onCalendar = {},
                navController = NavController(LocalContext.current),
                appViewModel = AppViewModel()
            )
        }
    }
}
