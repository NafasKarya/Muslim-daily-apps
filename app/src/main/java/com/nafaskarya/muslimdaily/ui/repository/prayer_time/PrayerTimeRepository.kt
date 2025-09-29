package com.nafaskarya.muslimdaily.ui.repository.prayer_time


import com.nafaskarya.muslimdaily.ui.data.prayer.PrayerSchedule
import com.nafaskarya.muslimdaily.ui.remotes.api.prayer_time.PrayerTimeApiService
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository for handling prayer time data operations.
 */
class PrayerTimeRepository(private val apiService: PrayerTimeApiService) {

    /**
     * Fetches the prayer schedule for a given location.
     * The result is wrapped in a [Flow] of [UiState] to handle loading, success, and error states.
     */
    fun getPrayerSchedule(latitude: Double, longitude: Double): Flow<UiState<PrayerSchedule>> = flow {
        emit(UiState.Loading)
        try {
            val schedule = apiService.getPrayerSchedule(latitude, longitude)
            emit(UiState.Success(schedule))
        } catch (e: Exception) {
            // Provide a more specific error message if possible
            emit(UiState.Error(e.message ?: "Failed to fetch prayer schedule"))
        }
    }
}
