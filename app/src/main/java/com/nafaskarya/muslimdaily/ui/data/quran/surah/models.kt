package com.nafaskarya.muslimdaily.ui.data.quran.surah

import com.google.gson.annotations.SerializedName

/**
 * Data model yang merepresentasikan detail sebuah surah, termasuk daftar ayat-ayatnya.
 * Ini adalah kelas utama yang akan Anda gunakan untuk parsing JSON dari API.
 */
data class SurahDetail(
    @SerializedName("number")
    val number: Int,

    @SerializedName("name")
    val arabicName: String,

    @SerializedName("englishName")
    val englishName: String,

    @SerializedName("englishNameTranslation")
    val englishNameTranslation: String,

    @SerializedName("revelationType")
    val revelationType: String,

    @SerializedName("numberOfAyahs")
    val numberOfAyahs: String,

    @SerializedName("ayahs")
    val ayahs: List<Ayah>
)

/**
 * Data model yang merepresentasikan satu ayat (verse) di dalam surah.
 */
data class Ayah(
    @SerializedName("numberInSurah")
    val numberInSurah: Int,

    @SerializedName("text")
    val arabicText: String,

    @SerializedName("translation")
    val translation: String,

    @SerializedName("audio")
    val audio: String
)
