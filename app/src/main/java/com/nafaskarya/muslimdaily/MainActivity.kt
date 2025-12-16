package com.nafaskarya.muslimdaily

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Jalankan AppActivity sebagai entry UI utama
        startActivity(Intent(this, com.nafaskarya.muslimdaily.presentation.core.AppActivity::class.java))

        // Tutup MainActivity supaya gak bisa balik ke sini
        finish()
    }
}
