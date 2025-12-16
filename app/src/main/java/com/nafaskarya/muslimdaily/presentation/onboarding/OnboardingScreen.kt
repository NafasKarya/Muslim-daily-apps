package com.nafaskarya.muslimdaily.presentation.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun OnboardingScreen(
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val dimen = rememberWindowDimensions()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_onboarding),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimen.width * 0.06f)
                .padding(top = dimen.height * 0.15f, bottom = dimen.height * 0.05f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoSection(
                size = dimen.getResponsiveTextSize(0.25f, min = 80f, max = 150f).value.dp
            )

            Spacer(modifier = Modifier.weight(1f))

            ContentSection(
                dimen = dimen,
                onLoginClick = onLoginClick,
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Composable
private fun LogoSection(size: androidx.compose.ui.unit.Dp) {
    Image(
        painter = painterResource(id = R.drawable.img_logo),
        contentDescription = "App Logo",
        modifier = Modifier
            .height(size) // Tinggi responsif sesuai parameter size
            .wrapContentWidth(),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun ContentSection(
    dimen: com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val titleSize = dimen.getResponsiveTextSize(0.06f, min = 20f, max = 32f)
    val dateSize = dimen.getResponsiveTextSize(0.05f, min = 16f, max = 28f)
    val welcomeSize = dimen.getResponsiveTextSize(0.045f, min = 14f, max = 24f)

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "GET CLOSER TO ALLAH",
            fontSize = titleSize,
            fontWeight = FontWeight.Black,
            color = Color.White,
            letterSpacing = 0.5.sp
        )

        Text(
            text = "Temukan ketenangan dan vibes positif di tiap hari ✨",
            fontSize = dateSize,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 8.dp)
        )


        Spacer(modifier = Modifier.height(dimen.height * 0.04f))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ASSALAMUALAIKUM",
                fontSize = welcomeSize,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(welcomeSize.value.dp * 1.2f)
            )
        }

        Spacer(modifier = Modifier.height(dimen.height * 0.03f))

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            border = BorderStroke(1.5.dp, Color.White)
        ) {
            Text(
                text = "Log in",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append("Don't have an account? ")
                }
                withStyle(style = SpanStyle(color = Color(0xFFE57373), fontWeight = FontWeight.Bold)) {
                    append("Sign in")
                }
            }

            Text(
                text = annotatedString,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }
}

@Preview(device = Devices.PIXEL_4, showBackground = true)
@Preview(device = Devices.PIXEL_C, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}