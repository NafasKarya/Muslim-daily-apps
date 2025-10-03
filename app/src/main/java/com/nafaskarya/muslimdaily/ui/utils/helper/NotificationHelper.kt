package com.nafaskarya.muslimdaily.ui.utils.helper

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nafaskarya.muslimdaily.MainActivity
import com.nafaskarya.muslimdaily.R

object NotificationHelper {

    // Gunakan konstanta agar tidak ada salah ketik
    private const val CHANNEL_ID = "DAILY_REMINDER_CHANNEL_ID"
    private const val NOTIFICATION_ID = 101

    fun showDailyReminderNotification(context: Context) {
        // 1. Buat Intent untuk membuka MainActivity saat notifikasi di-klik
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // 2. Buat Notifikasi menggunakan Builder
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // GANTI DENGAN NAMA IKON ANDA
            .setContentTitle("🕌 Waktunya Mengingat Allah")
            .setContentText("Jangan lupa untuk membaca dzikir pagi dan petang hari ini.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent) // Atur aksi saat di-klik
            .setAutoCancel(true) // Notifikasi hilang saat di-klik

        // 3. Tampilkan Notifikasi menggunakan NotificationManagerCompat
        with(NotificationManagerCompat.from(context)) {
            // Periksa kembali izin sebelum menampilkan notifikasi
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Jika karena alasan tertentu izin tidak ada, jangan lakukan apa-apa.
                // Log ini akan membantu debugging.
                println("Notification permission not granted.")
                return
            }
            // Tampilkan notifikasi dengan ID unik
            notify(NOTIFICATION_ID, builder.build())
            println("Notification shown successfully.")
        }
    }
}