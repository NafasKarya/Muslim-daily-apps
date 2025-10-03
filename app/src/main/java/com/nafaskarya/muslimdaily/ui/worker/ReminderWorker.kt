package com.nafaskarya.muslimdaily.ui.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nafaskarya.muslimdaily.ui.utils.helper.NotificationHelper
import timber.log.Timber

// --- PERBAIKAN DI SINI ---
class ReminderWorker(
    context: Context, // Hapus `private val` dari sini
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        // Tambahkan log untuk memastikan worker berjalan
        Timber.d("ReminderWorker: doWork() is running.")
        return try {
            NotificationHelper.showDailyReminderNotification(applicationContext)
            Timber.d("ReminderWorker: NotificationHelper called successfully.")
            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "ReminderWorker: Error during doWork.")
            Result.retry()
        }
    }
}