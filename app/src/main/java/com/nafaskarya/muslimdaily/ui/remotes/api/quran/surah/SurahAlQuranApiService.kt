package com.nafaskarya.muslimdaily.ui.remotes.api.quran.surah

import com.nafaskarya.muslimdaily.ui.data.quran.surah.SurahDetail
import com.nafaskarya.muslimdaily.ui.utils.constant.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service untuk endpoint detail Surah.
 * Disederhanakan karena satu endpoint sudah mengembalikan semua data.
 */
interface SurahAlQuranApiService {

    /**
     * Mengambil detail lengkap sebuah surah, termasuk semua ayat di dalamnya.
     */
    @GET(AppConstants.ENDPOINT_QURAN_SURAH_DETAIL)
    suspend fun getSurahDetail(
        @Path("surahNumber") surahNumber: Int
    ): SurahDetail // Tipe kembalian adalah SurahDetail yang berisi List<Ayah>
}

