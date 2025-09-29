package com.nafaskarya.muslimdaily.components.shared.loader

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun LoaderPage(
    modifier: Modifier = Modifier
) {
    // Menggunakan daftar teks dari file DataLoad.kt
    var currentText by remember { mutableStateOf(loadingTexts.first()) }

    // Efek untuk mengganti teks secara acak setiap 3 detik
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L) // Jeda selama 3 detik
            // Ambil teks acak baru yang tidak sama dengan teks saat ini
            val newText = loadingTexts.filter { it != currentText }.random()
            currentText = newText
        }
    }

    // 1. Gradasi Warna Latar Belakang
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color.White,       // Warna putih
            Color(0xFFBDE3C3)  // Warna hijau mint
        )
    )

    // 2. Animasi untuk Efek Gradasi Berkilau (Shimmer)
    val shimmerTransition = rememberInfiniteTransition(label = "shimmer transition")
    val shimmerTranslate by shimmerTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1200f, // Jarak pergerakan gradasi
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing)
        ),
        label = "shimmer translate"
    )

    // Gradasi linear untuk teks
    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            Color.Gray.copy(alpha = 0.6f), // Warna teks redup
            Color.DarkGray,                // Warna teks terang (puncak kilau)
            Color.Gray.copy(alpha = 0.6f), // Kembali ke warna redup
        ),
        start = Offset(shimmerTranslate - 200f, shimmerTranslate - 200f),
        end = Offset(shimmerTranslate, shimmerTranslate)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundBrush), // Terapkan gradasi latar belakang
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 3. Teks yang berganti-ganti dengan animasi Crossfade
            Crossfade(targetState = currentText, animationSpec = tween(500), label = "text crossfade") { text ->
                Text(
                    text = text,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center, // Atur perataan teks
                    style = TextStyle(
                        brush = shimmerBrush // Terapkan gradasi berkilau pada teks
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoaderPagePreview() {
    LoaderPage()
}

