package com.example.dosport.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.dosport.R
import com.example.dosport.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    navController: NavController,
    profilePicture: Painter = painterResource(id = R.drawable.ic_profile_placeholder), // ensure you place the placeholder image in the res/drawable directory
    userName: String,
    userEmail: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAvatarChange: () -> Unit,
    onLanguageChange: (String) -> Unit,
    onLogout: () -> Unit
) {
    var newName by remember { mutableStateOf(userName) }
    var newEmail by remember { mutableStateOf(userEmail) }
    var newPassword by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }
    val languages = listOf("English", "Spanish")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp), // padding в 32.dp, как в LoginPage
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Profile Picture
        Box(
            modifier = Modifier
                .size(120.dp) // увеличим размер немного
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(3.dp)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
        ) {
            Image(
                painter = profilePicture,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Change Avatar Button
        Button(onClick = onAvatarChange, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Change Avatar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // User Name
        BasicTextField(
            value = newName,
            onValueChange = {
                newName = it
                onNameChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary) // border как на LoginPage
                .padding(16.dp),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
                    if (newName.isEmpty()) {
                        Text(
                            text = "Name",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // User Email
        BasicTextField(
            value = newEmail,
            onValueChange = {
                newEmail = it
                onEmailChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary) // border как на LoginPage
                .padding(16.dp),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
                    if (newEmail.isEmpty()) {
                        Text(
                            text = "Email",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password Change
        BasicTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                onPasswordChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary) // border как на LoginPage
                .padding(16.dp),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
                    if (newPassword.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Language Switch
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                readOnly = true,
                value = selectedLanguage,
                onValueChange = {},
                label = { Text("Language") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = { Text(language) },
                        onClick = {
                            selectedLanguage = language
                            onLanguageChange(language)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Logout Button
        Button(
            onClick = onLogout,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(end = 16.dp)
                .width(200.dp)
        ) {
            Text("Logout")
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
fun ProfilePagePreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp) {
            ProfilePage(
                profilePicture = painterResource(id = R.drawable.ic_profile_placeholder), // replace with actual drawable resource
                userName = "John Doe",
                userEmail = "johndoe@example.com",
                onNameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onAvatarChange = {},
                onLanguageChange = {},
                onLogout = {},
                navController = NavController(LocalContext.current)
            )
        }

    }
}

