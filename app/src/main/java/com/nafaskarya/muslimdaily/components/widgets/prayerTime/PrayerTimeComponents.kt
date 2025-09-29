// File: com/nafaskarya/muslimdaily/components/widgets/prayerTime/NewPrayerTimeCardUI.kt

package com.nafaskarya.muslimdaily.components.widgets.prayerTime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.ui.data.hijri.HijriDate
import com.nafaskarya.muslimdaily.ui.utils.state.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.utils.state.UiState

/**
 * The main UI component for displaying the prayer time card.
 * This composable is now streamlined to only show the upcoming prayer details and Hijri date.
 */
@Composable
internal fun NewPrayerTimeCardUI(
    state: PrayerTimeUiState.Success,
    hijriState: UiState<HijriDate>,
    countdown: String // <-- UBAH DI SINI: Menerima countdown sebagai parameter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        TopPrayerSection(state, hijriState, countdown) // <-- UBAH DI SINI: Teruskan countdown
    }
}

/**
 * Displays the main header of the prayer card, including the background image,
 * upcoming prayer info, and dates.
 */
@Composable
private fun TopPrayerSection(
    state: PrayerTimeUiState.Success,
    hijriState: UiState<HijriDate>,
    countdown: String // <-- UBAH DI SINI: Menerima countdown sebagai parameter
) {
    val upcomingPrayerName = state.prayerData.nextPrayerName
    val upcomingPrayerTime = state.prayerData.nextPrayerTime
    // val countdown = state.prayerData.countdownToNext // <-- HAPUS BARIS INI

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        Image(
            painter = painterResource(id = state.cardImage),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val textStyle = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            when (hijriState) {
                is UiState.Loading -> Text(text = "Memuat tanggal...", style = textStyle)
                is UiState.Success -> {
                    val date = hijriState.data
                    Text(text = "${date.day} ${date.monthName} ${date.year} H", style = textStyle)
                }
                is UiState.Error -> Text(text = "Tanggal Hijriah tidak tersedia", style = textStyle)
            }

            Text(
                text = state.formattedDate,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "$upcomingPrayerName $upcomingPrayerTime",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            // UBAH DI SINI: Gunakan parameter countdown yang baru
            CountdownPill(timeRemaining = countdown)
        }
    }
}

/**
 * A small pill-shaped component to display the countdown timer.
 */
@Composable
private fun CountdownPill(timeRemaining: String) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.15f))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = "Countdown",
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = timeRemaining,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}