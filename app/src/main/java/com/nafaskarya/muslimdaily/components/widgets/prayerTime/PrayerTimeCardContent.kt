// File: com/nafaskarya/muslimdaily/components/widgets/prayerTime/PrayerTimeCardContent.kt

package com.nafaskarya.muslimdaily.components.widgets.prayerTime

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData

@Composable
fun PrayerTimeCardContent(
    prayerData: PrayerTimesData,
    upcomingPrayerPeriod: PrayerPeriod,
    @DrawableRes imageRes: Int, // <-- Menerima resource gambar, bukan warna
    showStars: Boolean,
    gregorianDateText: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // Box digunakan untuk menumpuk gambar dan konten
        Box(modifier = Modifier.fillMaxSize()) {
            // 1. LAPISAN GAMBAR (PALING BAWAH)
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                // ContentScale.Crop memastikan gambar menutupi seluruh area Card
                contentScale = ContentScale.Crop
            )

            // 2. LAPISAN SCRIM (TENGAH)
            // Overlay gelap transparan agar teks mudah dibaca
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            // 3. LAPISAN KONTEN (PALING ATAS)
            Box(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "$gregorianDateText\n" +
                            "$upcomingPrayerPeriod",
                    color = Color.White // Teks dibuat putih agar kontras
                )
            }
        }
    }
}