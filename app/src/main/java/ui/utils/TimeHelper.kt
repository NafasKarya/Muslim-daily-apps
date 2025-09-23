package com.nafaskarya.muslimdaily.ui.utils

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import com.nafaskarya.muslimdaily.R
import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

sealed class TimeOfDay(
    open val greetingText: String,
    val backgroundColor: Color,
    val textColor: Color,
    @DrawableRes val cardImage: Int
) {
    // Sunnah Prayers
    object Tahajud : TimeOfDay("Tahajud, Yuk,", Color(0xFF637AB9), Color.White, R.drawable.img_tahajud)
    object Syuruq : TimeOfDay("Syuruq, Yuk,", Color(0xFFFFD580), Color(0xFF3A3A3A), R.drawable.img_syuruq)
    object Dhuha : TimeOfDay("Dhuha, Yuk,", Color(0xFFFFF2CC), Color(0xFF3A3A3A), R.drawable.img_dhuha)

    // Obligatory Prayers
    object LateNight : TimeOfDay("Selamat Istirahat,", Color(0xFF637AB9), Color.White, R.drawable.img_fajr)
    object Morning : TimeOfDay("Assalamualaikum,", Color(0xFF234C6A), Color.White, R.drawable.img_mosque)
    object Afternoon : TimeOfDay("Assalamualaikum,", Color(0xFFF8F7BA), Color(0xFF3A3A3A), R.drawable.img_tahajud)
    object Evening : TimeOfDay("Assalamualaikum,", Color(0xFFFFC29B), Color(0xFF3A3A3A), R.drawable.img_tahajud)
    object Night : TimeOfDay("Selamat Istirahat,", Color(0xFF234C6A), Color.White, R.drawable.img_fajr)
}

// Data holder for dynamic greeting
data class TimeGreeting(
    val timeOfDay: TimeOfDay,
    val greetingText: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentTimeGreeting(): TimeGreeting {
    val currentTime = LocalTime.now()
    val currentDay = LocalDate.now().dayOfWeek
    val hour = currentTime.hour
    val minute = currentTime.minute

    val baseTimeOfDay = when {
        (hour in 1..3) -> TimeOfDay.Tahajud
        (hour == 6 && minute in 0..29) -> TimeOfDay.Syuruq
        (hour == 6 && minute >= 30) || (hour in 7..10) || (hour == 11 && minute == 0) -> TimeOfDay.Dhuha
        (hour in 11..14 && !(hour == 11 && minute == 0)) -> TimeOfDay.Afternoon
        (hour in 15..18) -> TimeOfDay.Evening
        (hour in 19..23) || (hour == 0) -> TimeOfDay.Night
        (hour == 4 && minute >= 30) || (hour == 5) -> TimeOfDay.Morning
        else -> TimeOfDay.LateNight
    }

    // Monday & Thursday Sunnah Fasting
    val isFastingTomorrow = (
            (currentDay == DayOfWeek.SUNDAY && hour >= 18) || // Sunday night for Monday fasting
                    (currentDay == DayOfWeek.WEDNESDAY && hour >= 18) || // Wednesday night for Thursday fasting
                    (currentDay == DayOfWeek.MONDAY && hour < 4) || // Early Monday before Subuh
                    (currentDay == DayOfWeek.THURSDAY && hour < 4) // Early Thursday before Subuh
            )
    if (isFastingTomorrow) {
        return TimeGreeting(baseTimeOfDay, "Puasa Sunnah, Yuk")
    }

    val isFastingDay = (
            (currentDay == DayOfWeek.MONDAY || currentDay == DayOfWeek.THURSDAY) &&
                    (hour >= 4 && hour < 18)
            )
    if (isFastingDay) {
        return TimeGreeting(baseTimeOfDay, "Bismillah Istiqomah")
    }

    return TimeGreeting(baseTimeOfDay, baseTimeOfDay.greetingText)
}
