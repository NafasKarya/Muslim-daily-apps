package com.nafaskarya.muslimdaily // Pastikan package Anda benar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nafaskarya.muslimdaily.onboarding.OnboardingActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        // --- TAMBAHKAN KODE INI ---
        // Atur status bar dan navigation bar agar transparan
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )

        super.onCreate(savedInstanceState)

        // Hapus setContent karena splash screen dari library sudah menangani UI.
        // Cukup navigasi ke activity selanjutnya.

        // Aksi setelah animasi splash selesai
        startActivity(Intent(this, OnboardingActivity::class.java))
        finish() // Tutup SplashActivity agar tidak bisa kembali
    }
}