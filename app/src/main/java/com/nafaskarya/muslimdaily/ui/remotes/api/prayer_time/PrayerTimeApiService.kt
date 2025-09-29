package com.nafaskarya.muslimdaily.ui.remotes.api.prayer_time

import com.nafaskarya.muslimdaily.ui.data.prayer.PrayerSchedule
import com.nafaskarya.muslimdaily.ui.utils.constant.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for prayer time related endpoints.
 */
interface PrayerTimeApiService {

    @GET(AppConstants.ENDPOINT_PRAYER_SCHEDULE)
    suspend fun getPrayerSchedule(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): PrayerSchedule
}

