package com.nafaskarya.muslimdaily // Pastikan nama package ini sesuai dengan proyek Anda

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Panggil fungsi untuk membuat channel notifikasi saat aplikasi pertama kali dibuat
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Pembuatan channel hanya diperlukan untuk Android 8.0 (API 26) ke atas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Pengingat Harian"
            val descriptionText = "Channel untuk notifikasi pengingat harian Muslim Daily"
            // Tingkat kepentingan notifikasi
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            // ID Channel harus unik
            val channel = NotificationChannel("DAILY_REMINDER_CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            // Daftarkan channel ke sistem Android
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}