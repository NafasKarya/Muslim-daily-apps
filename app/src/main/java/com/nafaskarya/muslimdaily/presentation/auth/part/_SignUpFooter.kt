package com.nafaskarya.muslimdaily.presentation.auth.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.constant.GreenPrimary
import com.nafaskarya.muslimdaily.presentation.core.constant.TextGray

@Composable
fun SignUpFooter(onSignUpClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextGray)) {
            append("Don't have an account? ")
        }
        withStyle(style = SpanStyle(color = GreenPrimary, fontWeight = FontWeight.Bold)) {
            append("Sign up")
        }
    }

    Box(modifier = androidx.compose.ui.Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            text = annotatedString,
            fontSize = 14.sp,
            modifier = androidx.compose.ui.Modifier.clickable { onSignUpClick() }
        )
    }
}
