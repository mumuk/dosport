package com.example.dosport.ui.components.forms

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dosport.ui.screens.FormType
import com.example.dosport.ui.screens.LoginPage
import com.example.dosport.ui.theme.AppTheme

@Composable
fun LoginForm(onSwitchForm: (String) -> Unit, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("admin@example.com")) }
    var password by remember { mutableStateOf(TextFieldValue("admin")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login Form",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .height(56.dp),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
                    if (email.text.isEmpty()) {
                        Text(
                            text = "Email",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .height(56.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (password.text.isEmpty()) {
                            Text(
                                text = "Password",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        innerTextField()
                    }
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (email.text == "admin@example.com" && password.text == "admin") {
                    onLoginSuccess()
                } else {
                    loginError = true
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(end = 16.dp)
                .width(200.dp)
        ) {
            Text("Login")
        }
        if (loginError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Invalid email or password",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onSwitchForm(FormType.FORGOT_PASSWORD.name) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Forgot password?")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onSwitchForm(FormType.REGISTER.name) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Don't have an account? Register")
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
fun LoginPreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp) {
            LoginForm(onSwitchForm = {}, onLoginSuccess = {})

        }
    }
}





//@Composable
//fun LoginForm(onSwitchForm: (String) -> Unit, onLoginSuccess: () -> Unit) {
//    var email by remember { mutableStateOf(TextFieldValue("admin@example.com")) }
//    var password by remember { mutableStateOf(TextFieldValue("admin")) }
//    var loginError by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Login Form",
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        BasicTextField(
//            value = email,
//            onValueChange = { email = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .border(1.dp, MaterialTheme.colorScheme.primary),
//            decorationBox = { innerTextField ->
//                Box(modifier = Modifier.padding(16.dp)) {
//                    if (email.text.isEmpty()) {
//                        Text(
//                            text = "Email",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                    }
//                    innerTextField()
//                }
//            }
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        BasicTextField(
//            value = password,
//            onValueChange = { password = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .border(1.dp, MaterialTheme.colorScheme.primary),
//            decorationBox = { innerTextField ->
//                Box(modifier = Modifier.padding(16.dp)) {
//                    if (password.text.isEmpty()) {
//                        Text(
//                            text = "Password",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                    }
//                    innerTextField()
//                }
//            }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//
//                if (email.text == "admin@example.com" && password.text == "admin") {
//                    onLoginSuccess()
//                } else {
//                    loginError = true
//                }
//            },
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(end = 16.dp)
//                .width(200.dp)
//        ) {
//            Text("Login")
//        }
//        if (loginError) {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Invalid email or password",
//                color = MaterialTheme.colorScheme.error,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        TextButton(
//            onClick = { onSwitchForm(FormType.FORGOT_PASSWORD.name)},
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(end = 16.dp)
//        ) {
//            Text("Forgot password?")
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        TextButton(
//            onClick = { onSwitchForm(FormType.REGISTER.name) },
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(end = 16.dp)
//        ) {
//            Text("Don't have an account? Register")
//        }
//    }
//}