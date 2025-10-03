package com.nafaskarya.muslimdaily.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.nafaskarya.muslimdaily.ui.repository.notification.SettingsRepository
import com.nafaskarya.muslimdaily.ui.worker.ReminderWorker
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SettingsViewModel(
    private val repository: SettingsRepository,
    private val context: Context
) : ViewModel() {

    // Memberikan state ke UI
    val isReminderEnabled = repository.isDailyReminderEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private val workManager = WorkManager.getInstance(context)
    private val workName = "daily_reminder_work"

    fun onReminderToggled(isEnabled: Boolean) {
        viewModelScope.launch {
            // Simpan preferensi pengguna
            repository.setDailyReminder(isEnabled)

            if (isEnabled) {

                // --- OPSI 1: UNTUK TESTING CEPAT (Gunakan ini sekarang) ---
                // Menjadwalkan notifikasi untuk muncul satu kali setelah 10 detik.
                val testRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .build()
                workManager.enqueue(testRequest)


                /*
                // --- OPSI 2: UNTUK PRODUKSI (Gunakan ini nanti setelah testing selesai) ---
                // Menjadwalkan notifikasi untuk berulang setiap 24 jam.
                val reminderRequest = PeriodicWorkRequestBuilder<ReminderWorker>(
                    24, TimeUnit.HOURS
                ).build()

                workManager.enqueueUniquePeriodicWork(
                    workName,
                    ExistingPeriodicWorkPolicy.KEEP,
                    reminderRequest
                )
                */

            } else {
                // Batalkan pekerjaan periodik jika dinonaktifkan
                workManager.cancelUniqueWork(workName)
                // Jika Anda juga ingin membatalkan semua test request saat switch dimatikan:
                // workManager.cancelAllWork()
            }
        }
    }
}

// Factory untuk ViewModel
class SettingsViewModelFactory(private val repository: SettingsRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

