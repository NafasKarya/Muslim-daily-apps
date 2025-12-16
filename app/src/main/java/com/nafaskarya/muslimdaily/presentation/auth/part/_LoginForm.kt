package com.nafaskarya.muslimdaily.presentation.auth.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import com.nafaskarya.muslimdaily.presentation.core.constant.BorderColor
import com.nafaskarya.muslimdaily.presentation.core.constant.GreenPrimary
import com.nafaskarya.muslimdaily.presentation.core.constant.TextGray
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isRemembered: Boolean,
    onRememberChange: (Boolean) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
    ) {
        // === EMAIL FIELD ===
        Text(
            text = "Email",
            fontSize = 17.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            placeholder = { Text("jance@gmail.com", color = TextGray, fontSize = 15.sp) },
            shape = RoundedCornerShape(5.dp), // 🔥 radius 5dp
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = BorderColor,
                focusedBorderColor = GreenPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        // === PASSWORD FIELD ===
        Text(
            text = "Password",
            fontSize = 17.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            placeholder = { Text("••••••••", color = TextGray, fontSize = 15.sp) },
            shape = RoundedCornerShape(10.dp), // 🔥 radius 5dp
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = BorderColor,
                focusedBorderColor = GreenPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password",
                        tint = TextGray
                    )
                }
            }
        )

        // === LUPA PASSWORD ===
        Text(
            text = "Lupa kata sandi?",
            fontSize = 14.sp,
            color = GreenPrimary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 24.dp)
                .align(Alignment.End)
                .clickable { onForgotPasswordClick() }
        )
    }
}
