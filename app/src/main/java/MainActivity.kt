package com.nafaskarya.muslimdaily // Pastikan package Anda benar

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.nafaskarya.muslimdaily.guest.GuestDashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Aktifkan mode Edge-to-Edge sebelum super.onCreate()
        // Ini adalah praktik terbaik untuk menghindari flicker.
        enableEdgeToEdge(
            // 2. Gunakan .auto untuk status bar yang "pintar"
            // Ikon akan otomatis terang/gelap menyesuaikan tema.
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                GuestDashboard()
            }
        }
    }
}