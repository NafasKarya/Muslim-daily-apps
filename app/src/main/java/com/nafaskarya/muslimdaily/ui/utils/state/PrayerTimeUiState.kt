package com.nafaskarya.muslimdaily.ui.utils.state

import androidx.annotation.DrawableRes
import com.nafaskarya.muslimdaily.ui.data.prayer.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.data.prayer.PrayerSchedule

/**
 * Represents the specific UI states for the Prayer Time feature.
 */
sealed interface PrayerTimeUiState {
    /**
     * Represents the initial loading state before any data is available.
     */
    data object Loading : PrayerTimeUiState

    /**
     * Represents a successful data fetch.
     *
     * This state contains all necessary data, pre-processed and ready for display in the UI.
     *
     * @property prayerData The raw schedule data from the API.
     * @property upcomingPrayerPeriod The next prayer period to be highlighted.
     * @property greeting A time-appropriate greeting message.
     * @property cardImage The drawable resource ID for the background image.
     * @property formattedDate A display-ready date string.
     * @property isRefreshing Indicates if the data is being refreshed in the background (e.g., via pull-to-refresh).
     */
    data class Success(
        val prayerData: PrayerSchedule,
        val upcomingPrayerPeriod: PrayerPeriod,
        val greeting: String,
        @DrawableRes val cardImage: Int,
        val formattedDate: String,
        val isRefreshing: Boolean = false
    ) : PrayerTimeUiState

    /**
     * Represents an error state.
     * @param message A user-friendly error message to be displayed.
     */
    data class Error(val message: String) : PrayerTimeUiState
}

