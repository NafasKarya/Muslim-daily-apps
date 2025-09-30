package com.nafaskarya.muslimdaily

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nafaskarya.muslimdaily.components.shared.quran.QuranScreen
import com.nafaskarya.muslimdaily.guest.GuestDashboard



class MainActivity : ComponentActivity() {
    // Tambahkan anotasi @RequiresApi karena GuestDashboard membutuhkannya
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "dashboard_route"
                    ) {
                        composable("dashboard_route") {
                            GuestDashboard(navController = navController)
                        }

                        // --- PERUBAHAN 2: Ganti placeholder dengan layar asli ---
                        composable("quran_route") {
                            QuranScreen(navController = navController)
                        }

                        composable("adzan_route") {
                            PlaceholderScreen(text = "Halaman Jadwal Adzan")
                        }

                        composable("qibla_route") {
                            PlaceholderScreen(text = "Halaman Arah Qiblat")
                        }
                    }
                }
            }
        }
    }
}

// Composable sementara untuk halaman yang belum dibuat
@Composable
fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}