package com.nafaskarya.muslimdaily.presentation.auth.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.Alignment
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.presentation.core.constant.BorderColor
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite

@Composable
fun SocialButtons(
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SocialButton(
            text = "Google Play",
            iconRes = R.drawable.img_logo, // Ganti ke ic_google jika ada
            modifier = Modifier.weight(1f),
            onClick = onGoogleClick
        )
        SocialButton(
            text = "Apple Store",
            iconRes = R.drawable.img_logo, // Ganti ke ic_apple jika ada
            modifier = Modifier.weight(1f),
            onClick = onAppleClick
        )
    }
}

@Composable
fun SocialButton(
    text: String,
    iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, BorderColor),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
