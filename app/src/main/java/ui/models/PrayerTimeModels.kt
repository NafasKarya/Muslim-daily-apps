package com.nafaskarya.muslimdaily.ui.models

/**
 * Enum untuk merepresentasikan periode waktu sholat.
 * Ini membuat kode lebih mudah dibaca daripada menggunakan string biasa.
 */
enum class PrayerPeriod {
    FAJR, DHUHR, ASR, MAGHRIB, ISHA
}

/**
 * Data class untuk menampung data waktu sholat yang sudah diproses.
 * @param cityName Nama kota dari lokasi yang didapat.
 * @param times Peta yang menghubungkan setiap periode sholat dengan waktunya dalam format String.
 */
data class PrayerTimesData(
    val cityName: String,
    val times: Map<PrayerPeriod, String>
)