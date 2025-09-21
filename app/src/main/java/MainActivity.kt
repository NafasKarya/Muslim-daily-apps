package com.nafaskarya.muslimdaily

import android.graphics.Color // <-- Import ini
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle // <-- Import ini
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.nafaskarya.muslimdaily.guest.GuestDashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Modifikasi enableEdgeToEdge untuk memastikan ikon status bar gelap
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )

        setContent {
            MaterialTheme {
                GuestDashboard()
            }
        }
    }
}