// File: PrayerTimeComponents.kt
package com.nafaskarya.muslimdaily.components.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/**
 * Composable untuk menampilkan latar belakang bintang-bintang.
 * Digunakan pada waktu Maghrib, Isya, dan Subuh.
 */
@Composable
internal fun StarsBackground(modifier: Modifier = Modifier) {
    val stars = remember {
        // Membuat daftar posisi dan ukuran bintang secara acak saat pertama kali compose
        List(150) {
            Offset(x = Random.nextFloat(), y = Random.nextFloat()) to (Random.nextFloat() * 1.5f + 0.5f)
        }
    }
    Canvas(modifier = modifier.fillMaxSize()) {
        stars.forEach { (position, radius) ->
            drawCircle(
                color = Color.White.copy(alpha = Random.nextFloat()),
                radius = radius,
                center = Offset(position.x * size.width, position.y * size.height)
            )
        }
    }
}

/**
 * Composable untuk menampilkan satu item waktu sholat (misal: Subuh 04:26).
 * @param name Nama waktu sholat (e.g., "Subuh").
 * @param time Waktu sholat dalam format string (e.g., "04:26").
 * @param isActive Menandakan apakah ini adalah waktu sholat berikutnya.
 */
@Composable
internal fun PrayerTimeItem(name: String, time: String, isActive: Boolean) {
    val activeColor = Color(0xFF4FB7B3)
    val inactiveColor = Color.Gray.copy(alpha = 0.8f)
    val textColor = if (isActive) activeColor else inactiveColor
    val textWeight = if (isActive) FontWeight.Bold else FontWeight.Normal

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = name, fontSize = 14.sp, color = textColor, fontWeight = textWeight)
        Spacer(Modifier.height(4.dp))
        Text(text = time, fontSize = 14.sp, color = textColor, fontWeight = textWeight)
    }
}

/**
 * Composable untuk menampilkan kartu status, misalnya saat izin lokasi ditolak.
 * @param statusText Pesan yang akan ditampilkan di kartu.
 */
@Composable
internal fun PrayerTimeStatusCard(statusText: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = statusText,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}