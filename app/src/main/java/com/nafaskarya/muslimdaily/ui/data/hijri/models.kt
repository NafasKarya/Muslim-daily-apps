package com.nafaskarya.muslimdaily.ui.data.hijri // Sesuaikan dengan package Anda

import com.google.gson.annotations.SerializedName

data class HijriDate(
    @SerializedName("year")
    val year: Int,

    @SerializedName("month")
    val month: Int, // Tipe data diubah menjadi Int

    @SerializedName("day")
    val day: Int,

    @SerializedName("month_name")
    val monthName: String, // Variabel disesuaikan dengan key JSON

    @SerializedName("day_name")
    val dayName: String // Variabel disesuaikan dengan key JSON
)

