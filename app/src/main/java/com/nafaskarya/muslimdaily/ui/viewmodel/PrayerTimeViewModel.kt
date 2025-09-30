package com.nafaskarya.muslimdaily.ui.viewmodel

import android.os.Build
// import android.util.Log // <-- Hapus atau komentari import ini
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nafaskarya.muslimdaily.ui.data.prayer.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.data.prayer.PrayerSchedule
import com.nafaskarya.muslimdaily.ui.repository.prayer_time.PrayerTimeRepository
import com.nafaskarya.muslimdaily.ui.utils.getCurrentTimeGreeting
import com.nafaskarya.muslimdaily.ui.utils.state.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber // --- 1. TAMBAHKAN IMPORT TIMBER ---
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Manages UI state and data logic for the Prayer Time feature.
 * It receives its dependencies via constructor injection.
 */
@RequiresApi(Build.VERSION_CODES.O)
class PrayerTimeViewModel(private val repository: PrayerTimeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PrayerTimeUiState>(PrayerTimeUiState.Loading)
    val uiState: StateFlow<PrayerTimeUiState> = _uiState.asStateFlow()

    // --- BARU: StateFlow untuk menampung string countdown ---
    private val _countdownState = MutableStateFlow("00:00:00")
    val countdownState: StateFlow<String> = _countdownState.asStateFlow()

    // --- BARU: Job untuk mengontrol coroutine countdown ---
    private var countdownJob: Job? = null

    /**
     * Triggers a data refresh for prayer times for a specific location.
     */
    fun refreshData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is PrayerTimeUiState.Success) {
                _uiState.value = currentState.copy(isRefreshing = true)
            } else {
                _uiState.value = PrayerTimeUiState.Loading
            }

            repository.getPrayerSchedule(latitude, longitude).collect { result ->
                when (result) {
                    is UiState.Success -> {
                        // --- 2. TAMBAHKAN LOG TIMBER DI SINI ---
                        // Ini akan mencetak seluruh isi data yang berhasil di-parse dari JSON.
                        Timber.d("Data mentah diterima dari repository: ${result.data}")
                        // ------------------------------------------
                        _uiState.value = mapDataToSuccessState(result.data)
                    }
                    is UiState.Error -> {
                        // --- 3. GANTI LOG DENGAN TIMBER ---
                        Timber.e("Error fetching prayer data: ${result.message}")
                        _uiState.value = PrayerTimeUiState.Error(result.message)
                    }
                    is UiState.Loading -> {
                        // The UI is already showing a loading state, no action needed.
                    }
                }
            }
        }
    }

    /**
     * Maps the raw [PrayerSchedule] data from the repository into a rich [PrayerTimeUiState.Success].
     */
    private fun mapDataToSuccessState(
        prayerData: PrayerSchedule,
        isRefreshing: Boolean = false
    ): PrayerTimeUiState.Success {
        // --- TAMBAHKAN LOG TIMBER DI SINI JUGA UNTUK MEMASTIKAN ---
        Timber.d("Memetakan data ke Success State. nextPrayerTime: ${prayerData.nextPrayerTime}")
        // -----------------------------------------------------------

        val timeGreeting = getCurrentTimeGreeting()
        val upcomingPeriod = mapPrayerNameToPeriod(prayerData.nextPrayerName)

        // --- BARU: Memulai timer saat data sukses didapatkan ---
        startCountdownTimer(prayerData.nextPrayerTime)

        return PrayerTimeUiState.Success(
            prayerData = prayerData,
            upcomingPrayerPeriod = upcomingPeriod,
            greeting = timeGreeting.greetingText,
            cardImage = timeGreeting.timeOfDay.cardImage,
            formattedDate = getFormattedDate(),
            isRefreshing = isRefreshing
        )
    }

    /**
     * --- BARU: Fungsi untuk memulai dan mengelola timer hitung mundur ---
     *
     * @param prayerTime String waktu sholat berikutnya (format "HH:mm").
     */
    private fun startCountdownTimer(prayerTime: String) {
        // Hentikan timer sebelumnya jika ada (misalnya saat refresh)
        countdownJob?.cancel()

        // Mulai coroutine baru untuk timer
        countdownJob = viewModelScope.launch {
            try {
                // Parser untuk waktu format "HH:mm"
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val targetDate = timeFormat.parse(prayerTime) ?: return@launch

                // Buat target waktu untuk hari ini
                val targetCalendar = Calendar.getInstance().apply {
                    val prayerCalendar = Calendar.getInstance().apply { time = targetDate }
                    set(Calendar.HOUR_OF_DAY, prayerCalendar.get(Calendar.HOUR_OF_DAY))
                    set(Calendar.MINUTE, prayerCalendar.get(Calendar.MINUTE))
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                // Jika waktu target sudah lewat untuk hari ini, set untuk besok
                if (targetCalendar.timeInMillis < System.currentTimeMillis()) {
                    targetCalendar.add(Calendar.DAY_OF_YEAR, 1)
                }

                // Loop akan berjalan selama coroutine aktif
                while (isActive) {
                    val currentTime = System.currentTimeMillis()
                    val diff = targetCalendar.timeInMillis - currentTime

                    if (diff > 0) {
                        val hours = TimeUnit.MILLISECONDS.toHours(diff)
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60
                        val seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60

                        _countdownState.value = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                    } else {
                        // Waktu habis, tampilkan nol dan hentikan loop
                        _countdownState.value = "00:00:00"
                        break
                    }
                    delay(1000) // Tunggu 1 detik
                }
            } catch (e: Exception) {
                // --- GANTI LOG DENGAN TIMBER ---
                Timber.e(e, "Error parsing prayer time or running countdown")
                _countdownState.value = "--:--:--" // Tampilkan error state
            }
        }
    }

    /**
     * Maps a prayer name string from the API to the corresponding [PrayerPeriod] enum.
     */
    private fun mapPrayerNameToPeriod(prayerName: String): PrayerPeriod {
        return when (prayerName.lowercase(Locale.ROOT)) {
            "fajr" -> PrayerPeriod.FAJR
            "dhuhr" -> PrayerPeriod.DHUHR
            "asr" -> PrayerPeriod.ASR
            "maghrib" -> PrayerPeriod.MAGHRIB
            "isha" -> PrayerPeriod.ISHA
            else -> PrayerPeriod.NONE
        }
    }

    /**
     * Returns the current date formatted for display in the UI.
     */
    private fun getFormattedDate(): String {
        val dateFormatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        return dateFormatter.format(Date())
    }
}

/**
 * Factory for creating [PrayerTimeViewModel] instances with a custom [PrayerTimeRepository] dependency.
 */
@Suppress("UNCHECKED_CAST")
class PrayerTimeViewModelFactory(private val repository: PrayerTimeRepository) :
    ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrayerTimeViewModel::class.java)) {
            return PrayerTimeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}