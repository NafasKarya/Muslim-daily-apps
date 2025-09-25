package com.nafaskarya.muslimdaily.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nafaskarya.muslimdaily.MainActivity

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- PERUBAHAN DI SINI ---
        // Atur status bar agar sepenuhnya transparan.
        // Ikon di status bar (jam, baterai) akan otomatis berwarna terang
        // karena latar belakang onboarding Anda gelap.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )

        setContent {
            OnboardingScreen(
                onFinish = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            )
        }
    }
}