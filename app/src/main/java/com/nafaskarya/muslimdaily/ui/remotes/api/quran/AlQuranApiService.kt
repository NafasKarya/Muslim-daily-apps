package com.nafaskarya.muslimdaily.ui.remotes.api.quran


import com.nafaskarya.muslimdaily.ui.data.quran.Surah
import com.nafaskarya.muslimdaily.ui.utils.constant.AppConstants
import retrofit2.http.GET

/**
 * Retrofit service interface for Quran related endpoints.
 */
interface AlQuranApiService {

    /**
     * Fetches the list of all surahs from the API.
     * This is a suspend function, intended to be called from a coroutine.
     *
     * @return A list of Surah objects.
     */
    @GET(AppConstants.ENDPOINT_QURAN_SURAHS)
    suspend fun getSurahs(): List<Surah>
}