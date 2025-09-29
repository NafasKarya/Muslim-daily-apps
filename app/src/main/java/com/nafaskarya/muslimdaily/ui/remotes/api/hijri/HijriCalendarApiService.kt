package com.nafaskarya.muslimdaily.ui.remotes.api.hijri // <-- Ganti "com.yourpackage.
import retrofit2.http.GET
import com.nafaskarya.muslimdaily.ui.data.hijri.HijriDate
import com.nafaskarya.muslimdaily.ui.utils.constant.AppConstants

interface HijriCalendarApiService {
    @GET(AppConstants.ENDPOINT_HIJRI_TODAY)
    suspend fun getTodayHijriDate(): HijriDate // Ganti 'HijriDate' dengan model Anda
}