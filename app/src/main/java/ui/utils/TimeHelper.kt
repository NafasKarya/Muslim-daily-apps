// File: com/nafaskarya/muslimdaily/utils/TimeHelper.kt

package com.nafaskarya.muslimdaily.ui.utils

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import com.nafaskarya.muslimdaily.R // Pastikan import R benar
import androidx.compose.ui.graphics.Color
import java.time.LocalTime

// Sealed class untuk merepresentasikan setiap periode waktu beserta warnanya
sealed class TimeOfDay(
    val greetingText: String,
    val backgroundColor: Color,
    val textColor: Color,
    @DrawableRes val cardImage: Int // <-- PROPERTI BARU DITAMBAHKAN
) {
    object LateNight : TimeOfDay("Bismillah Yuk", Color(0xFF31326F), Color.White, R.drawable.img_mosque)
    object Morning : TimeOfDay("Assalamualaikum", Color(0xFF234C6A), Color.White, R.drawable.img_mosque)
    object Afternoon : TimeOfDay("Assalamualaikum", Color(0xFFF8F7BA), Color(0xFF3A3A3A), R.drawable.img_mosque)
    object Evening : TimeOfDay("Assalamualaikum", Color(0xFFFFC29B), Color(0xFF3A3A3A), R.drawable.img_mosque)
    object Night : TimeOfDay("Assalamualaikum", Color(0xFF234C6A), Color.White, R.drawable.img_mosque)
}

/**
 * Fungsi untuk mendapatkan periode waktu saat ini berdasarkan jam.
 * @return Objek TimeOfDay yang berisi teks sapaan dan warna yang sesuai.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentTimeOfDay(): TimeOfDay {
    val currentHour = LocalTime.now().hour

    return when (currentHour) {
        in 0..3 -> TimeOfDay.LateNight  // 00:00 - 03:59
        in 4..10 -> TimeOfDay.Morning    // 04:00 - 10:59
        in 11..14 -> TimeOfDay.Afternoon // 11:00 - 14:59
        in 15..18 -> TimeOfDay.Evening   // 15:00 - 18:59
        else -> TimeOfDay.Night          // 19:00 - 23:59
    }
}