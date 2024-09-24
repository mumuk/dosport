package com.example.dosport.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import com.example.dosport.ui.components.generic.CustomButton
import com.example.dosport.ui.components.generic.list.GenericList
import com.example.dosport.ui.theme.AppTheme
import com.example.dosport.viewmodel.AppViewModel

@Composable
fun MainPage(
    appViewModel: AppViewModel,
    navController: NavController,
    programs: List<Program> = appViewModel.state.collectAsState().value.programState.programs,
    onEdit: (Program) -> Unit = { program ->
        println("onEdit called with program: $program")

        appViewModel.selectProgram(program)
    },
    onStart: (Program) -> Unit = { program ->
        println("onStart called with program: $program")
        appViewModel
            .selectProgram(program)
    },
    onCreate: () -> Unit = {
        appViewModel.clearDraftProgram()
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
                        GenericList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            items = programs,
                            selectedItem = selectedProgram,
                            onSelect = { selectedProgram = it }
                        )
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
                            text = "Start",
                            modifier = Modifier.width(100.dp)
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
                            text = "Edit",
                            modifier = Modifier.width(100.dp)
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



