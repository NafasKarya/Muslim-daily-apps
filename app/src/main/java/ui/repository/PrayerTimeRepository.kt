package ui.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
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

// --- KELAS EXCEPTION BARU ---
/**
 * Exception yang dilemparkan ketika izin lokasi tidak diberikan oleh pengguna.
 */
class LocationPermissionDeniedException(message: String = "Izin lokasi ditolak oleh pengguna.") : Exception(message)
// ----------------------------


private val Context.prayerTimesDataStore: DataStore<PrayerTimesData> by dataStore(
    fileName = "prayer_times.json",
    serializer = PrayerTimesSerializer
)

private suspend fun fetchFromFakeApi(cityName: String): PrayerTimesData {
    delay(1500)
    return PrayerTimesData(
        cityName = cityName,
        // Contoh tanggal Hijriah, sesuaikan dengan data asli nanti
        hijriDate = "25 Dzulqaidah 1446 H", // <-- TAMBAHKAN BARIS INI
        times = mapOf(
            PrayerPeriod.TAHAJUD to "02:45",
            PrayerPeriod.FAJR to "04:31",
            PrayerPeriod.SYURUQ to "05:45",
            PrayerPeriod.DHUHA to "06:15",
            PrayerPeriod.DHUHR to "11:51",
            PrayerPeriod.ASR to "15:13",
            PrayerPeriod.MAGHRIB to "17:46",
            PrayerPeriod.ISHA to "18:58"
        ),
        lastUpdated = System.currentTimeMillis()
    )
}

class PrayerTimeRepository(private val context: Context) {

    suspend fun getPrayerTimes(): PrayerTimesData = context.prayerTimesDataStore.data.first()

    suspend fun refreshPrayerTimesIfStale() {
        val cachedData = getPrayerTimes()
        val hoursSinceUpdate = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - cachedData.lastUpdated)
        if (hoursSinceUpdate < 6 && cachedData.cityName != "Tidak Diketahui") {
            return
        }
        // Tidak perlu try-catch di sini, biarkan exception dilempar ke ViewModel
        forceRefreshPrayerTimes()
    }

    /**
     * Fungsi ini untuk refresh paksa.
     * MELEMPARKAN (throws) Exception jika terjadi kegagalan.
     * @throws LocationPermissionDeniedException jika izin lokasi ditolak.
     * @throws IllegalStateException jika lokasi tidak dapat ditemukan karena alasan lain.
     */
    suspend fun forceRefreshPrayerTimes() {
        // --- PERUBAHAN UTAMA DI SINI ---
        // Pengecekan izin harusnya ada di dalam getCurrentLocation,
        // dan fungsi itu yang seharusnya melempar exception.

        // Cek izin di sini sebagai contoh
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw LocationPermissionDeniedException()
        }

        val location = getCurrentLocation(context) // Asumsikan fungsi ini sekarang aman dari sisi izin
        if (location != null) {
            val cityName = getCityNameFromCoordinates(context, location)
            val newApiData = fetchFromFakeApi(cityName)
            context.prayerTimesDataStore.updateData { newApiData }
        } else {
            // Lemparkan error jika lokasi tidak ditemukan karena alasan lain (misal: GPS mati)
            throw IllegalStateException("Tidak dapat menemukan lokasi. Pastikan GPS Anda aktif.")
        }
        // --------------------------------
    }

    private suspend fun fetchFromFakeApi(cityName: String): PrayerTimesData {
        delay(1500)
        return PrayerTimesData(
            cityName = cityName,
            times = mapOf(
                PrayerPeriod.TAHAJUD to "02:45",   // Tambah
                PrayerPeriod.FAJR to "04:31",
                PrayerPeriod.SYURUQ to "05:45",    // Tambah
                PrayerPeriod.DHUHA to "06:15",     // Tambah
                PrayerPeriod.DHUHR to "11:51",
                PrayerPeriod.ASR to "15:13",
                PrayerPeriod.MAGHRIB to "17:46",
                PrayerPeriod.ISHA to "18:58"
            ),
            lastUpdated = System.currentTimeMillis()
        )
    }

}