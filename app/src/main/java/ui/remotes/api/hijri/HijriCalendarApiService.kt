// Lokasi file: app/src/main/java/com/packageanda/ui/remotes/api/HijriCalendarApiService.kt

package ui.remotes.api.hijri // <-- Ganti "com.yourpackage.name"
import retrofit2.http.GET
import ui.data.hijri.HijriDate

interface HijriCalendarApiService {
    @GET(AppConstants.ENDPOINT_HIJRI_TODAY)
    suspend fun getTodayHijriDate(): HijriDate // Ganti 'HijriDate' dengan model Anda
}