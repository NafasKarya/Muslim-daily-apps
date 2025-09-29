package ui.utils.state

import androidx.annotation.DrawableRes
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData

sealed interface PrayerTimeUiState {
    data object Loading : PrayerTimeUiState
    data class Success(
        val prayerData: PrayerTimesData,
        val upcomingPrayerPeriod: PrayerPeriod,
        val showStars: Boolean,
        val formattedDate: String,
        @DrawableRes val cardImage: Int,
        val isRefreshing: Boolean = false,
        val greeting: String // <-- TAMBAHKAN BARIS INI
    ) : PrayerTimeUiState
    data class Error(val message: String) : PrayerTimeUiState
}