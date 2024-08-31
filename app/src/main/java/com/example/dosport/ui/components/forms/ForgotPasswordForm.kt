package com.example.dosport.ui.components.forms

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.dosport.ui.screens.FormType

import androidx.compose.material3.TextField
import java.util.regex.Pattern

@Composable
fun ForgotPasswordForm(onSwitchForm: (String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Password Reset Form",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TextField для ввода email
        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null // сброс ошибки при изменении текста
                successMessage = null // сброс успешного сообщения
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary),
            label = { Text("Email") },
            isError = emailError != null // подсветка поля в случае ошибки
        )

        if (emailError != null) {
            Text(
                text = emailError!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                emailError = validateEmail(email.text)
                if (emailError == null) {
                    successMessage = "A password reset link has been sent to your email"
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
        ) {
            Text("Reset Password")
        }

        if (successMessage != null) {
            Text(
                text = successMessage!!,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onSwitchForm(FormType.LOGIN.name) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Remembered your password? Log in")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onSwitchForm(FormType.REGISTER.toString()) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Don't have an account? Register")
        }
    }
}
