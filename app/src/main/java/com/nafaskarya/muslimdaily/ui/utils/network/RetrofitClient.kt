package com.nafaskarya.muslimdaily.ui.utils.network

import com.nafaskarya.muslimdaily.BuildConfig
import com.nafaskarya.muslimdaily.ui.remotes.api.hijri.HijriCalendarApiService
import com.nafaskarya.muslimdaily.ui.remotes.api.prayer_time.PrayerTimeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

/**
 * Singleton provider for Retrofit and its API services.
 * This object is now responsible for creating all API service instances.
 */
object RetrofitClient {

    /**
     * A single, shared Retrofit instance with a pre-configured OkHttpClient.
     * The client includes a logger that is only active in debug builds.
     */
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

    /**
     * Lazily provides the [HijriCalendarApiService].
     */
    val hijriApiService: HijriCalendarApiService by lazy {
        retrofit.create(HijriCalendarApiService::class.java)
    }

    /**
     * Lazily provides the [PrayerTimeApiService].
     */
    val prayerTimeApiService: PrayerTimeApiService by lazy {
        retrofit.create(PrayerTimeApiService::class.java)
    }
}

