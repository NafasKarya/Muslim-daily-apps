package com.nafaskarya.muslimdaily.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.nafaskarya.muslimdaily.MainActivity
import com.nafaskarya.muslimdaily.ui.screens.LoaderScreen // Pastikan import ini benar
import kotlinx.coroutines.delay

// 1. Definisikan state untuk setiap layar yang mungkin tampil
private sealed class ScreenState {
    object Onboarding : ScreenState()
    object Loading : ScreenState()
}

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )

        setContent {
            OnboardingFlow()
        }
    }

    @Composable
    private fun OnboardingFlow() {
        // 2. Buat state untuk melacak layar yang sedang aktif
        var currentScreen by remember { mutableStateOf<ScreenState>(ScreenState.Onboarding) }

        when (currentScreen) {
            is ScreenState.Onboarding -> {
                // Tampilkan OnboardingScreen.
                // Jika selesai, UBAH STATE menjadi Loading, jangan langsung pindah activity.
                OnboardingScreen(
                    onFinish = {
                        currentScreen = ScreenState.Loading
                    }
                )
            }
            is ScreenState.Loading -> {
                // Karena state berubah, LoaderScreen sekarang yang akan tampil.
                LoaderScreen()

                // 3. Gunakan LaunchedEffect untuk navigasi setelah loader tampil
                LaunchedEffect(Unit) {
                    // Simulasi loading
                    delay(2000) // Tunggu 2 detik

                    // Setelah selesai, baru pindah ke MainActivity
                    startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}