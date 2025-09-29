// File: com/nafaskarya/muslimdaily/components/shared/StarsBackground.kt

package com.nafaskarya.muslimdaily.components.shared.backgorund

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun StarsBackground(modifier: Modifier = Modifier, numberOfStars: Int = 100) {
    // BoxWithConstraints digunakan untuk mendapatkan ukuran Composablenya
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        // Membuat daftar posisi bintang secara acak.
        // `remember` dan `width/height` sebagai key agar bintang hanya dihitung ulang
        // jika ukuran Box berubah, bukan setiap recomposition.
        val stars = remember(width, height) {
            List(numberOfStars) {
                // Posisi acak di dalam area Box
                Offset(x = Random.nextFloat() * width, y = Random.nextFloat() * height)
            }
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            stars.forEach { offset ->
                // Menggambar lingkaran kecil sebagai bintang
                drawCircle(
                    color = Color.White.copy(alpha = Random.nextFloat().coerceAtLeast(0.4f)), // Alpha acak agar ada variasi terang
                    radius = Random.nextFloat() * 2f + 0.5f, // Ukuran acak antara 0.5dp dan 2.5dp
                    center = offset
                )
            }
        }
    }
}