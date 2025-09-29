package com.nafaskarya.muslimdaily.ui.data.prayer

import com.google.gson.annotations.SerializedName

/**
 * Represents the prayer schedule data returned from the API.
 * @SerializedName is used to map JSON keys to Kotlin properties.
 */
data class PrayerSchedule(
    @SerializedName("current_prayer_time")
    val currentPrayerTime: String,

    @SerializedName("current_prayer_name")
    val currentPrayerName: String,

    @SerializedName("next_prayer_time")
    val nextPrayerTime: String,

    @SerializedName("next_prayer_name")
    val nextPrayerName: String,

    @SerializedName("countdown_to_next")
    val countdownToNext: String,

    @SerializedName("date")
    val date: String
)

