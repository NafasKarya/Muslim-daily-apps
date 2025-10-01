package com.nafaskarya.muslimdaily.ui.utils.network

import com.nafaskarya.muslimdaily.BuildConfig
import com.nafaskarya.muslimdaily.ui.remotes.api.hijri.HijriCalendarApiService
import com.nafaskarya.muslimdaily.ui.remotes.api.prayer_time.PrayerTimeApiService
import com.nafaskarya.muslimdaily.ui.remotes.api.quran.AlQuranApiService
// Import service yang benar untuk detail surah dan ayah
import com.nafaskarya.muslimdaily.ui.remotes.api.quran.surah.SurahAlQuranApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitClient {

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(loggingInterceptor)
            }
        }.build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val hijriApiService: HijriCalendarApiService by lazy {
        retrofit.create(HijriCalendarApiService::class.java)
    }

    val prayerTimeApiService: PrayerTimeApiService by lazy {
        retrofit.create(PrayerTimeApiService::class.java)
    }

    val alQuranApiService: AlQuranApiService by lazy {
        retrofit.create(AlQuranApiService::class.java)
    }

    /**
     * --- INI YANG KITA BUTUHKAN ---
     * Menyediakan [SurahAlQuranApiService] untuk detail surah dan ayah.
     */
    val surahAlQuranApiService: SurahAlQuranApiService by lazy {
        retrofit.create(SurahAlQuranApiService::class.java)
    }
}
