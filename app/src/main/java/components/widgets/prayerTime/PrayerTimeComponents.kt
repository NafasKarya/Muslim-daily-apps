package com.nafaskarya.muslimdaily.components.widgets.prayerTime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
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
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import ui.data.hijri.HijriDate
import ui.utils.state.UiState

import ui.utils.state.PrayerTimeUiState
import java.util.Locale

@Composable
internal fun NewPrayerTimeCardUI(
    state: PrayerTimeUiState.Success,
    // --- 1. Terima hijriState sebagai parameter ---
    hijriState: UiState<HijriDate>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // --- 2. Teruskan hijriState ke TopPrayerSection ---
            TopPrayerSection(state, hijriState)
            BottomPrayerSection(
                times = state.prayerData.times,
                upcomingPeriod = state.upcomingPrayerPeriod
            )
        }
    }
}

@Composable
private fun TopPrayerSection(
    state: PrayerTimeUiState.Success,
    // --- 3. TopPrayerSection juga menerima hijriState ---
    hijriState: UiState<HijriDate>
) {
    val upcomingPrayerName = state.upcomingPrayerPeriod.name
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    val upcomingPrayerTime = state.prayerData.times[state.upcomingPrayerPeriod] ?: "--:--"

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

            // --- 4. Tampilkan UI berdasarkan hijriState ---
            val textStyle = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            when (hijriState) {
                is UiState.Loading -> {
                    Text(text = "Memuat tanggal...", style = textStyle)
                }
                is UiState.Success -> {
                    val date = hijriState.data
                    Text(
                        text = "${date.day} ${date.monthName} ${date.year} H",
                        style = textStyle
                    )
                }
                is UiState.Error -> {
                    Text(text = "Tanggal Hijriah tidak tersedia", style = textStyle)
                }
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
            CountdownPill(timeRemaining = "in 0h 10m")
        }
    }
}

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

@Composable
private fun BottomPrayerSection(
    times: Map<PrayerPeriod, String>,
    upcomingPeriod: PrayerPeriod
) {
    val prayerOrder = listOf(
        PrayerPeriod.FAJR,
        PrayerPeriod.DHUHR,
        PrayerPeriod.ASR,
        PrayerPeriod.MAGHRIB,
        PrayerPeriod.ISHA
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        prayerOrder.forEach { period ->
            val name = period.name.replaceFirstChar { it.titlecase(Locale.ROOT) }
            val time = times[period] ?: "--:--"
            PrayerTimeRowItem(
                name = name,
                time = time,
                isUpcoming = period == upcomingPeriod
            )
        }
    }
}

@Composable
private fun PrayerTimeRowItem(name: String, time: String, isUpcoming: Boolean) {
    val activeColor = Color(0xFFD32F2F)
    val inactiveColor = Color.Gray
    val textColor = if (isUpcoming) activeColor else Color.Black

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            color = if (isUpcoming) activeColor else inactiveColor,
            fontSize = 13.sp,
            fontWeight = if (isUpcoming) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = time,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        val indicatorHeight = 12.dp
        Box(modifier = Modifier.height(indicatorHeight)) {
            if (isUpcoming) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Upcoming prayer indicator",
                    tint = activeColor,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                        .offset(y = (-8).dp)
                )
            }
        }
    }
}
