package com.nafaskarya.muslimdaily.ui.utils.constant

/**
 * Defines constant values used throughout the application.
 */
object AppConstants {
    // BASE_URL is defined in build.gradle (BuildConfig)
    // const val BASE_URL = "http://127.0.0.1:8000/"

    const val ENDPOINT_HIJRI_TODAY = "api/hijri-date/today"
    const val ENDPOINT_PRAYER_SCHEDULE = "api/schedule/now"

    const val ENDPOINT_QURAN_SURAHS = "api/quran/surahs"
    const val ENDPOINT_QURAN_SURAH_DETAIL = "api/quran/surahs/{surahNumber}"
    const val ENDPOINT_QURAN_AYAH_DETAIL = "api/quran/surahs/{surahNumber}/ayahs/{ayahNumber}"
}

