package com.nafaskarya.muslimdaily.ui.viewmodel

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
        @DrawableRes val cardImage: Int, // <-- Menggunakan cardImage
        val isRefreshing: Boolean = false
    ) : PrayerTimeUiState
    data class Error(val message: String) : PrayerTimeUiState
}