package com.nafaskarya.muslimdaily.ui.data.quran // Package disesuaikan untuk Quran

import com.google.gson.annotations.SerializedName

/**
 * Data model representing a single Surah from the API response.
 */
data class Surah(
    @SerializedName("number")
    val number: Int,

    @SerializedName("name")
    val arabicName: String, // Variabel dibuat lebih deskriptif

    @SerializedName("englishName")
    val englishName: String,

    @SerializedName("englishNameTranslation")
    val englishNameTranslation: String,

    @SerializedName("numberOfAyahs")
    val numberOfAyahs: Int,

    @SerializedName("revelationType")
    val revelationType: String
)