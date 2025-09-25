package com.nafaskarya.muslimdaily

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
// import com.jakewharton.threetenabp.AndroidThreeTen // <-- 1. DIHAPUS, karena kita tidak pakai library ini
import com.nafaskarya.muslimdaily.guest.GuestDashboard

class MainActivity : ComponentActivity() {
    // Anotasi @RequiresApi juga bisa dihapus karena sudah tidak relevan
    override fun onCreate(savedInstanceState: Bundle?) {
        // AndroidThreeTen.init(this) // <-- 2. DIHAPUS, karena tidak diperlukan dengan Core Library Desugaring

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuestDashboard()
                }
            }
        }
    }
}