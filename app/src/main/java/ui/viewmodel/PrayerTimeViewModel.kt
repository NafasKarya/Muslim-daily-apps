package com.nafaskarya.muslimdaily.ui.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData
import com.nafaskarya.muslimdaily.ui.repository.PrayerTimeRepository
import com.nafaskarya.muslimdaily.ui.utils.TimeOfDay
import com.nafaskarya.muslimdaily.ui.utils.getCurrentTimeOfDay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class PrayerTimeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PrayerTimeRepository(application)

    private val _uiState = MutableStateFlow<PrayerTimeUiState>(PrayerTimeUiState.Loading)
    val uiState: StateFlow<PrayerTimeUiState> = _uiState.asStateFlow()

    init {
        refreshDataIfStale()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refreshDataIfStale() {
        viewModelScope.launch {
            _uiState.value = PrayerTimeUiState.Loading
            try {
                repository.refreshPrayerTimesIfStale()
                val data = repository.getPrayerTimes()
                _uiState.value = mapDataToSuccessState(data)
            } catch (e: Exception) {
                _uiState.value = PrayerTimeUiState.Error("Gagal mengambil data awal.")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refreshData() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is PrayerTimeUiState.Success) {
                _uiState.value = currentState.copy(isRefreshing = true)
                try {
                    repository.forceRefreshPrayerTimes()
                    val newData = repository.getPrayerTimes()
                    _uiState.value = mapDataToSuccessState(newData, isRefreshing = false)
                } catch (e: Exception) {
                    _uiState.value = currentState.copy(isRefreshing = false)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapDataToSuccessState(prayerData: PrayerTimesData, isRefreshing: Boolean = false): PrayerTimeUiState {
        if (prayerData.cityName == "Tidak Diketahui") {
            return PrayerTimeUiState.Loading
        }

        // Panggil helper untuk mendapatkan semua data UI terkait waktu
        val timeOfDay = getCurrentTimeOfDay()

        val upcomingPeriod = calculateUpcomingPrayer(prayerData)
        val formattedDate = getFormattedDate()

        return PrayerTimeUiState.Success(
            prayerData = prayerData,
            upcomingPrayerPeriod = upcomingPeriod,
            showStars = timeOfDay is TimeOfDay.Night || timeOfDay is TimeOfDay.LateNight,
            formattedDate = formattedDate,
            cardImage = timeOfDay.cardImage,
            isRefreshing = isRefreshing
        )
    }

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

    private fun getFormattedDate(): String {
        val dateFormatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        return dateFormatter.format(Calendar.getInstance().time)
    }
}