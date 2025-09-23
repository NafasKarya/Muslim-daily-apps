package com.nafaskarya.muslimdaily.ui.models

import kotlinx.serialization.Serializable

/**
 * Enum untuk merepresentasikan periode waktu sholat.
 */
enum class PrayerPeriod {
    TAHAJUD, // 🌃 Paling awal, biasanya jam 2-4 pagi
    FAJR,
    SYURUQ,  // Setelah Subuh, waktu syuruq (terbit matahari)
    DHUHA,   // Setelah syuruq sampai sebelum dzuhur
    DHUHR,
    ASR,
    MAGHRIB,
    ISHA
}

/**
 * Model data untuk jadwal sholat yang bisa disimpan (Serializable).
 */
@Serializable
data class PrayerTimesData(
    val cityName: String = "Tidak Diketahui",
    val times: Map<PrayerPeriod, String> = emptyMap(),
    val lastUpdated: Long = 0L // Timestamp untuk Caching
)
