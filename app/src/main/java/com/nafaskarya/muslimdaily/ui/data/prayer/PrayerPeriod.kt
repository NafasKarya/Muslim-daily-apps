package com.nafaskarya.muslimdaily.ui.data.prayer

/**
 * Represents the five daily prayer periods in a type-safe manner.
 */
enum class PrayerPeriod {
    FAJR,
    DHUHR,
    ASR,
    MAGHRIB,
    ISHA,
    /** Represents a state where no upcoming prayer is applicable (e.g., after Isha). */
    NONE
}

