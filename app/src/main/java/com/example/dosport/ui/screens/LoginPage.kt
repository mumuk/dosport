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
import com.example.dosport.ui.components.forms.ForgotPasswordForm
import com.example.dosport.ui.components.forms.LoginForm
import com.example.dosport.ui.components.forms.RegistrationForm

import com.example.dosport.ui.theme.AppTheme


enum class FormType {
    LOGIN, REGISTER, FORGOT_PASSWORD
}

@Composable
fun LoginPage(
    navController: NavController,
    onLoginSuccess: () -> Unit,
    onRegister: () -> Unit
) {
    var currentForm by remember { mutableStateOf(FormType.LOGIN) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
        when (currentForm) {
            FormType.REGISTER -> RegistrationForm(
                onSwitchForm = { currentForm = FormType.valueOf(it) },
                onRegister = onRegister  // Передаем onRegister
            )
            FormType.LOGIN -> LoginForm(
                onSwitchForm = { currentForm = FormType.valueOf(it) },
                onLoginSuccess = onLoginSuccess
            )
            FormType.FORGOT_PASSWORD -> ForgotPasswordForm(
                onSwitchForm = { currentForm = FormType.valueOf(it) }
            )
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
            LoginPage(navController = NavController(LocalContext.current), onLoginSuccess = {}, onRegister = {})
        }
    }
}