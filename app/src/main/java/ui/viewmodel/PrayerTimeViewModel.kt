package com.nafaskarya.muslimdaily.ui.viewmodel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData
import com.nafaskarya.muslimdaily.ui.repository.PrayerTimeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

sealed interface PrayerTimeUiState {
    data object Loading : PrayerTimeUiState
    data class Success(
        val prayerData: PrayerTimesData,
        val upcomingPrayerPeriod: PrayerPeriod,
        val showStars: Boolean,
        val cardColor: Color,
        val formattedDate: String,
        val isRefreshing: Boolean = false // State refresh digabung di sini
    ) : PrayerTimeUiState
    data class Error(val message: String) : PrayerTimeUiState
}

class PrayerTimeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PrayerTimeRepository(application)

    private val _uiState = MutableStateFlow<PrayerTimeUiState>(PrayerTimeUiState.Loading)
    val uiState: StateFlow<PrayerTimeUiState> = _uiState.asStateFlow()

    // State 'isRefreshing' yang terpisah sudah tidak diperlukan lagi

    init {
        // Saat ViewModel dibuat, langsung panggil fungsi untuk load data awal
        refreshDataIfStale()
    }

    // Fungsi untuk load data awal (dipanggil saat init)
    fun refreshDataIfStale() {
        viewModelScope.launch {
            _uiState.value = PrayerTimeUiState.Loading // Tampilkan shimmer/loading awal
            try {
                repository.refreshPrayerTimesIfStale()
                // Setelah refresh, ambil data terbaru dan buat state Success
                val data = repository.getPrayerTimes()
                _uiState.value = mapDataToSuccessState(data)
            } catch (e: Exception) {
                _uiState.value = PrayerTimeUiState.Error("Gagal mengambil data awal.")
            }
        }
    }

    // Fungsi untuk pull-to-refresh (dipanggil dari UI)
    fun refreshData() {
        viewModelScope.launch {
            val currentState = _uiState.value
            // Hanya bisa refresh jika state saat ini adalah Success
            if (currentState is PrayerTimeUiState.Success) {
                // 1. Tampilkan shimmer dengan mengubah state isRefreshing menjadi true
                _uiState.value = currentState.copy(isRefreshing = true)

                try {
                    // 2. Panggil refresh paksa
                    repository.forceRefreshPrayerTimes()
                    // 3. Ambil data terbaru dari repository
                    val newData = repository.getPrayerTimes()
                    // 4. Buat state Success baru dengan data baru dan matikan shimmer
                    _uiState.value = mapDataToSuccessState(newData, isRefreshing = false)
                } catch (e: Exception) {
                    // Jika error, matikan shimmer dan kembali ke state data sebelumnya
                    _uiState.value = currentState.copy(isRefreshing = false)
                }
            }
        }
    }

    // Fungsi helper untuk mengubah data mentah menjadi state Success
    private fun mapDataToSuccessState(prayerData: PrayerTimesData, isRefreshing: Boolean = false): PrayerTimeUiState {
        // Jika data masih default (kosong), anggap masih loading
        if (prayerData.cityName == "Tidak Diketahui") {
            return PrayerTimeUiState.Loading
        }
        val upcomingPeriod = calculateUpcomingPrayer(prayerData)
        val showStars = upcomingPeriod in listOf(PrayerPeriod.MAGHRIB, PrayerPeriod.ISHA, PrayerPeriod.FAJR)
        val cardColor = getCardColor(upcomingPeriod)
        val formattedDate = getFormattedDate()
        return PrayerTimeUiState.Success(
            prayerData = prayerData,
            upcomingPrayerPeriod = upcomingPeriod,
            showStars = showStars,
            cardColor = cardColor,
            formattedDate = formattedDate,
            isRefreshing = isRefreshing
        )
    }

    // Fungsi-fungsi private lainnya (tidak berubah)
    private fun calculateUpcomingPrayer(prayerData: PrayerTimesData): PrayerPeriod {
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val now = Calendar.getInstance()

        val prayerCalendars = prayerData.times.mapValues { (_, timeStr) ->
            runCatching {
                Calendar.getInstance().apply {
                    val parsedTime = timeFormatter.parse(timeStr)
                    if (parsedTime != null) {
                        val cal = Calendar.getInstance()
                        cal.time = parsedTime
                        set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY))
                        set(Calendar.MINUTE, cal.get(Calendar.MINUTE))
                        set(Calendar.SECOND, 0)
                    }
                }
            }.getOrNull()
        }

        val sortedPrayers = listOf(
            PrayerPeriod.FAJR to prayerCalendars[PrayerPeriod.FAJR],
            PrayerPeriod.DHUHR to prayerCalendars[PrayerPeriod.DHUHR],
            PrayerPeriod.ASR to prayerCalendars[PrayerPeriod.ASR],
            PrayerPeriod.MAGHRIB to prayerCalendars[PrayerPeriod.MAGHRIB],
            PrayerPeriod.ISHA to prayerCalendars[PrayerPeriod.ISHA]
        )

        for ((period, cal) in sortedPrayers) {
            if (cal != null && now.before(cal)) {
                return period
            }
        }
        return PrayerPeriod.FAJR
    }

    private fun getCardColor(period: PrayerPeriod): Color {
        return when (period) {
            PrayerPeriod.FAJR -> Color(0xFF637AB9)
            PrayerPeriod.DHUHR -> Color(0xFF4CAF50)
            PrayerPeriod.ASR -> Color(0xFFEF7722)
            PrayerPeriod.MAGHRIB -> Color(0xFFC1554D)
            PrayerPeriod.ISHA -> Color(0xFF31326F)
        }
    }

    private fun getFormattedDate(): String {
        val dateFormatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        return dateFormatter.format(Calendar.getInstance().time)
    }
}