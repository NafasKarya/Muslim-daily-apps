package com.nafaskarya.muslimdaily.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.nafaskarya.muslimdaily.MainActivity

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // Memanggil Composable OnboardingScreen dan memberinya perintah
                OnboardingScreen(
                    onStartClicked = {
                        // Aksi ini akan dipanggil dari dalam OnboardingScreen
                        startActivity(Intent(this, MainActivity::class.java))
                        finish() // Tutup OnboardingActivity agar tidak bisa kembali
                    }
                )
            }
        }
    }
}