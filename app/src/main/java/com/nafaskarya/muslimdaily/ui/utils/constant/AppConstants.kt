package com.nafaskarya.muslimdaily.ui.utils.constant

/**
 * Defines constant values used throughout the application.
 */
object AppConstants {
    // Note: BASE_URL is now defined in build.gradle (BuildConfig) and should be removed from here
    // to avoid confusion. This is left for context.
    // const val BASE_URL = "http://127.0.0.1:8000/" 

    const val ENDPOINT_HIJRI_TODAY = "api/hijri-date/today"

    /**
     * Endpoint for fetching prayer schedules based on location.
     * The latitude and longitude are passed as query parameters.
     */
    const val ENDPOINT_PRAYER_SCHEDULE = "api/schedule/now"
}
