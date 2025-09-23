package com.nafaskarya.muslimdaily // Pastikan package Anda benar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nafaskarya.muslimdaily.onboarding.OnboardingActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            // Memanggil Composable SplashScreen dari file terpisah
            SplashScreen {
                // Aksi setelah animasi splash selesai
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }
    }
}