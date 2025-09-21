package com.nafaskarya.muslimdaily.components.items

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons

@Composable
fun PrayerTimeItem(name: String, time: String, isActive: Boolean) {
    val activeColor = Color(0xFFD32F2F) // Merah
    val inactiveColor = Color.Gray

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            color = if (isActive) activeColor else inactiveColor,
            fontSize = 14.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = time,
            color = if (isActive) activeColor else inactiveColor,
            fontSize = 14.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
        // Blok 'if (isActive)' yang berisi Icon telah dihapus.
    }
}