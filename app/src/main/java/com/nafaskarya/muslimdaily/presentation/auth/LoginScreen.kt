package com.nafaskarya.muslimdaily.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.nafaskarya.muslimdaily.presentation.auth.part.*
import com.nafaskarya.muslimdaily.presentation.core.constant.GreenPrimary
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onAppleClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    val dimen = rememberWindowDimensions()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRemembered by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LoginHeader(height = dimen.height * 0.4f)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimen.height * 0.46f) // 🔥 diturunin lagi dari sebelumnya (0.38f → 0.46f)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = dimen.width * 0.06f)
                        .padding(top = 32.dp, bottom = 36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginForm(
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it },
                        isRemembered = isRemembered,
                        onRememberChange = { isRemembered = it },
                        onForgotPasswordClick = onForgotPasswordClick
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
                    ) {
                        Text(
                            text = "Log in",
                            fontSize = 16.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    OrDivider()
                    Spacer(modifier = Modifier.height(24.dp))

                    SocialButtons(
                        onGoogleClick = onGoogleClick,
                        onAppleClick = onAppleClick
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    SignUpFooter(onSignUpClick = onSignUpClick)
                }
            }
        }
    }
}
