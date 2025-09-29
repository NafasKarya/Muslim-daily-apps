package com.nafaskarya.muslimdaily.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable layar penuh yang hanya menampilkan indikator loading di tengah.
 * Layar ini digunakan saat aplikasi sedang memuat data awal atau melakukan proses di latar belakang.
 *
 * @param modifier Modifier untuk kustomisasi dari luar.
 */
@Composable
fun LoaderScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White), // Sesuai permintaan, latar belakang disetel menjadi putih.
        contentAlignment = Alignment.Center
    ) {
        // Indikator loading circular standar dari Material Design.
        CircularProgressIndicator(
            // Warnanya secara default akan mengambil dari `primary` color theme Anda.
            // Anda bisa mengubahnya jika perlu, contoh: color = Color.Gray
        )
    }
}

/**
 * Preview untuk LoaderScreen agar mudah dilihat di Android Studio.
 */
@Preview(showBackground = true, name = "Loader Screen Preview")
@Composable
private fun LoaderScreenPreview() {
    // Sebaiknya bungkus preview dengan tema aplikasi Anda untuk konsistensi visual.
    // YourAppTheme {
    LoaderScreen()
    // }
}