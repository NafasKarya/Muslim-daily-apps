package com.nafaskarya.muslimdaily.ui.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData
import com.nafaskarya.muslimdaily.ui.utils.PrayerTimesSerializer
import com.nafaskarya.muslimdaily.ui.utils.getCityNameFromCoordinates
import com.nafaskarya.muslimdaily.ui.utils.getCurrentLocation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

private val Context.prayerTimesDataStore: DataStore<PrayerTimesData> by dataStore(
    fileName = "prayer_times.json",
    serializer = PrayerTimesSerializer
)

class PrayerTimeRepository(private val context: Context) {

    // --- PERUBAHAN UTAMA DI SINI ---
    /**
     * Mengambil data waktu sholat saat ini dari cache DataStore.
     * Diubah dari Flow menjadi suspend fun untuk kontrol yang lebih eksplisit.
     */
    suspend fun getPrayerTimes(): PrayerTimesData = context.prayerTimesDataStore.data.first()
    // ----------------------------

    /**
     * Fungsi ini untuk load awal, hanya akan mengambil data baru jika cache sudah usang.
     */
    suspend fun refreshPrayerTimesIfStale() {
        val cachedData = getPrayerTimes() // <-- Memanggil fungsi yang sudah diubah
        val hoursSinceUpdate = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - cachedData.lastUpdated)
        if (hoursSinceUpdate < 6 && cachedData.cityName != "Tidak Diketahui") {
            return // Data masih segar, tidak perlu refresh
        }
        // Jika data usang, panggil refresh paksa
        forceRefreshPrayerTimes()
    }

    /**
     * Fungsi ini untuk refresh paksa yang dipanggil oleh pull-to-refresh.
     * Selalu mengambil data baru dan mengabaikan cache check.
     */
    suspend fun forceRefreshPrayerTimes() {
        val location = getCurrentLocation(context)
        if (location != null) {
            val cityName = getCityNameFromCoordinates(context, location)
            val newApiData = fetchFromFakeApi(cityName)
            context.prayerTimesDataStore.updateData { newApiData }
        } else {
            // Lemparkan error jika lokasi tidak ditemukan agar bisa ditangkap oleh ViewModel
            throw IllegalStateException("Tidak dapat menemukan lokasi.")
        }
    }

    /**
     * Ganti ini dengan panggilan API sungguhan (Retrofit/Ktor) di masa depan.
     */
    private suspend fun fetchFromFakeApi(cityName: String): PrayerTimesData {
        delay(1500) // Simulasi loading agar indikator refresh terlihat
        return PrayerTimesData(
            cityName = cityName,
            times = mapOf(
                PrayerPeriod.FAJR to "04:31",
                PrayerPeriod.DHUHR to "11:51",
                PrayerPeriod.ASR to "15:13",
                PrayerPeriod.MAGHRIB to "17:46",
                PrayerPeriod.ISHA to "18:58"
            ),
            lastUpdated = System.currentTimeMillis()
        )
    }
}