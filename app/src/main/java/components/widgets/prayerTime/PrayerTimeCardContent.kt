package com.nafaskarya.muslimdaily.components.widgets.prayerTime

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData

@Composable
fun PrayerTimeCardContent(
    prayerData: PrayerTimesData,
    upcomingPrayerPeriod: PrayerPeriod,
    cardColor: Color,
    showStars: Boolean,
    gregorianDateText: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Lokasi: ${prayerData.cityName}\n" +
                        "Tanggal: $gregorianDateText\n" +
                        "Berikutnya: $upcomingPrayerPeriod"
            )
        }
    }
}