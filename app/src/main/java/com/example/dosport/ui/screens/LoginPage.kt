package com.example.dosport.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dosport.ui.theme.AppTheme


enum class FormType {
    LOGIN, REGISTER, FORGOT_PASSWORD
}

@Composable
fun LoginPage(navController: NavController) {
    var currentForm by remember { mutableStateOf(FormType.LOGIN) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
        when (currentForm) {
            FormType.REGISTER -> RegistrationForm(onSwitchForm = {
                currentForm = FormType.valueOf(it)
            })

            FormType.LOGIN -> LoginForm(onSwitchForm = { currentForm = FormType.valueOf(it) })
            FormType.FORGOT_PASSWORD -> ForgotPasswordForm(onSwitchForm = {
                currentForm = FormType.valueOf(it)
            })
        }
    }
}

@Composable
fun RegistrationForm(onSwitchForm: (String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "Registration Form", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(
            Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary),
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
                .border(1.dp, MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
                    if (password.text.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* handle registration */ }, modifier = Modifier.align(
            Alignment.CenterHorizontally).padding(end = 16.dp).width(200.dp)) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { onSwitchForm(FormType.LOGIN.toString()) },modifier = Modifier.align(
            Alignment.CenterHorizontally).padding(end = 16.dp)) {
            Text("Already have an account? Log in")
        }
    }
}


@Composable
fun LoginForm(onSwitchForm: (String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "Login Form", style = MaterialTheme.typography.titleLarge,modifier = Modifier.align(
            Alignment.CenterHorizontally) )
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = TextFieldValue(""),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary),
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
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
                    if (password.text.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* handle login */ }, modifier = Modifier.align(
            Alignment.CenterHorizontally).padding(end = 16.dp).width(200.dp)) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { onSwitchForm("forgotPassword") },modifier = Modifier.align(
            Alignment.CenterHorizontally).padding(end = 16.dp)) {
            Text("Forgot password?")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { onSwitchForm("registration") }, modifier = Modifier.align(
            Alignment.CenterHorizontally).padding(end = 16.dp)) {
            Text("Don't have an account? Register")
        }
    }
}

@Composable
fun ForgotPasswordForm(onSwitchForm: (String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "Password Reset Form", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(
            Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary),
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
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* handle password reset */ }, modifier = Modifier.align(
            Alignment.CenterHorizontally).width(200.dp)) {
            Text("Reset Password")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { onSwitchForm(FormType.LOGIN.toString()) },modifier = Modifier.align(
            Alignment.CenterHorizontally)) {
            Text("Remembered your password? Log in")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { onSwitchForm(FormType.REGISTER.toString()) }, modifier = Modifier.align(
            Alignment.CenterHorizontally)) {
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
            LoginPage( navController = NavController(LocalContext.current))
        }
    }
}