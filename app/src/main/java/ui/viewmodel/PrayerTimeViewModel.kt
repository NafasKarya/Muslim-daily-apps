package ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData
import com.nafaskarya.muslimdaily.ui.utils.TimeOfDay
import com.nafaskarya.muslimdaily.ui.utils.getCurrentTimeGreeting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ui.repository.LocationPermissionDeniedException
import ui.repository.PrayerTimeRepository
import ui.utils.state.PrayerTimeUiState
import java.text.SimpleDateFormat
import java.util.*

class PrayerTimeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PrayerTimeRepository(application)

    private val _uiState = MutableStateFlow<PrayerTimeUiState>(PrayerTimeUiState.Loading)
    val uiState: StateFlow<PrayerTimeUiState> = _uiState.asStateFlow()

    init {
        refreshDataIfStale()
    }

    // Fungsi refreshDataIfStale() dan refreshData() tidak ada perubahan...
    fun refreshDataIfStale() {
        viewModelScope.launch {
            _uiState.value = PrayerTimeUiState.Loading
            try {
                repository.refreshPrayerTimesIfStale()
                val data = repository.getPrayerTimes()
                _uiState.value = mapDataToSuccessState(data)
            } catch (e: LocationPermissionDeniedException) {
                Log.w("PrayerTimeViewModel", "Izin lokasi ditolak saat memuat awal:", e)
                _uiState.value = PrayerTimeUiState.Error("Izin lokasi diperlukan untuk menampilkan data.")
            } catch (e: Exception) {
                Log.e("PrayerTimeViewModel", "Gagal mengambil data awal:", e)
                _uiState.value = PrayerTimeUiState.Error("Gagal mengambil data awal.")
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is PrayerTimeUiState.Success) {
                _uiState.value = currentState.copy(isRefreshing = true)
                try {
                    repository.forceRefreshPrayerTimes()
                    val newData = repository.getPrayerTimes()
                    _uiState.value = mapDataToSuccessState(newData, isRefreshing = false)
                } catch (e: LocationPermissionDeniedException) {
                    Log.w("PrayerTimeViewModel", "Izin lokasi ditolak saat refresh manual:", e)
                    _uiState.value = PrayerTimeUiState.Error("Izin lokasi diperlukan untuk refresh.")
                } catch (e: Exception) {
                    Log.e("PrayerTimeViewModel", "Gagal melakukan refresh manual:", e)
                    _uiState.value = currentState.copy(isRefreshing = false)
                }
            } else if (currentState is PrayerTimeUiState.Error) {
                refreshDataIfStale()
            }
        }
    }


    // --- PERUBAHAN UTAMA ADA DI FUNGSI INI ---
    private fun mapDataToSuccessState(
        prayerData: PrayerTimesData,
        isRefreshing: Boolean = false
    ): PrayerTimeUiState {
        if (prayerData.cityName == "Tidak Diketahui") {
            return PrayerTimeUiState.Error("Gagal mendapatkan lokasi. Mohon berikan izin dan coba lagi.")
        }

        val timeGreeting = getCurrentTimeGreeting()
        val timeOfDay = timeGreeting.timeOfDay

        val upcomingPeriod = calculateUpcomingPrayer(prayerData)
        val formattedDate = getFormattedDate()

        return PrayerTimeUiState.Success(
            prayerData = prayerData,
            upcomingPrayerPeriod = upcomingPeriod,
            showStars = timeOfDay is TimeOfDay.Night || timeOfDay is TimeOfDay.LateNight,
            formattedDate = formattedDate,
            cardImage = timeOfDay.cardImage,
            isRefreshing = isRefreshing,
            greeting = timeGreeting.greetingText // <-- KIRIMKAN DATA SAPAAN DI SINI
        )
    }

    // Fungsi calculateUpcomingPrayer() dan getFormattedDate() tidak ada perubahan...
    private fun calculateUpcomingPrayer(prayerData: PrayerTimesData): PrayerPeriod {
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val now = Calendar.getInstance()

        val prayerOrder = listOf(
            PrayerPeriod.TAHAJUD,
            PrayerPeriod.FAJR,
            PrayerPeriod.SYURUQ,
            PrayerPeriod.DHUHA,
            PrayerPeriod.DHUHR,
            PrayerPeriod.ASR,
            PrayerPeriod.MAGHRIB,
            PrayerPeriod.ISHA
        )

        val prayerCalendars = prayerData.times.mapNotNull { (period, timeStr) ->
            runCatching {
                val parsedTime = timeFormatter.parse(timeStr) ?: return@runCatching null
                val tempCal = Calendar.getInstance().apply { time = parsedTime }
                val prayerCal = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, tempCal.get(Calendar.HOUR_OF_DAY))
                    set(Calendar.MINUTE, tempCal.get(Calendar.MINUTE))
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                if (prayerCal.before(now)) {
                    prayerCal.add(Calendar.DAY_OF_YEAR, 1)
                }
                period to prayerCal
            }.getOrNull()
        }.toMap()

        val upcomingPrayer = prayerOrder
            .mapNotNull { period -> prayerCalendars[period]?.let { period to it } }
            .minByOrNull { (_, cal) -> cal.timeInMillis }

        return upcomingPrayer?.first ?: prayerOrder.first()
    }

    private fun getFormattedDate(): String {
        val dateFormatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        return dateFormatter.format(Calendar.getInstance().time)
    }
}