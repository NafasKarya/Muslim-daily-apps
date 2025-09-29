package ui.utils.network // <-- Package disesuaikan

// --- IMPORT YANG DITAMBAHKAN ---
import com.nafaskarya.muslimdaily.BuildConfig // 1. Import BuildConfig dari package utama
import kotlin.getValue // 2. Import untuk 'by lazy' agar berfungsi
// --------------------------------

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.remotes.api.hijri.HijriCalendarApiService

object RetrofitClient {

    val instance: HijriCalendarApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL) // Sekarang BuildConfig akan dikenali
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(HijriCalendarApiService::class.java)
    }
}

