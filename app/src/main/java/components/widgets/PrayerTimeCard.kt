package com.nafaskarya.muslimdaily.components.widgets.prayertime

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.nafaskarya.muslimdaily.components.widgets.PrayerTimeItem
import com.nafaskarya.muslimdaily.components.widgets.PrayerTimeStatusCard
import com.nafaskarya.muslimdaily.components.widgets.ShimmerPrayerTimeCard
import com.nafaskarya.muslimdaily.components.widgets.StarsBackground
import com.nafaskarya.muslimdaily.layouts.theme.AppImages
import com.nafaskarya.muslimdaily.ui.models.PrayerPeriod
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData
import com.nafaskarya.muslimdaily.ui.utils.getCityNameFromCoordinates
import com.nafaskarya.muslimdaily.ui.utils.getCurrentLocation
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
private fun PrayerTimeHeader(cityName: String, onLocationClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Jadwal Sholat", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { onLocationClick() }
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(cityName, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }
}

@Composable
private fun PrayerTimeCardContent(
    prayerData: PrayerTimesData,
    upcomingPrayerPeriod: PrayerPeriod,
    cardColor: Color,
    showStars: Boolean
) {
    val dateFormatter = remember { SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID")) }
    val gregorianDateText = remember { dateFormatter.format(Calendar.getInstance().time) }

    val upcomingPrayerName = when (upcomingPrayerPeriod) {
        PrayerPeriod.FAJR -> "Subuh"
        PrayerPeriod.DHUHR -> "Dzuhur"
        PrayerPeriod.ASR -> "Ashar"
        PrayerPeriod.MAGHRIB -> "Maghrib"
        PrayerPeriod.ISHA -> "Isya"
    }
    val upcomingPrayerTime = prayerData.times[upcomingPrayerPeriod] ?: ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(start = 24.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
            ) {
                if (showStars) StarsBackground()
                Image(
                    painter = painterResource(AppImages.PrayerTime),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height(200.dp)
                        .width(210.dp)
                        .offset(x = 45.dp, y = 25.dp)
                        .zIndex(1f),
                    contentScale = ContentScale.Crop
                )
                Column(Modifier.align(Alignment.TopStart)) {
                    Text(prayerData.cityName, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Text(gregorianDateText, color = Color.White, fontSize = 12.sp)
                }
                Text(
                    text = "$upcomingPrayerName $upcomingPrayerTime",
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                prayerData.times.entries.forEach { (period, time) ->
                    val pName = when (period) {
                        PrayerPeriod.FAJR -> "Subuh"
                        PrayerPeriod.DHUHR -> "Dzuhur"
                        PrayerPeriod.ASR -> "Ashar"
                        PrayerPeriod.MAGHRIB -> "Maghrib"
                        PrayerPeriod.ISHA -> "Isya"
                    }
                    PrayerTimeItem(pName, time, isActive = upcomingPrayerPeriod == period)
                }
            }
        }
    }
}

@Composable
fun PrayerTimeCard() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var prayerData by rememberSaveable { mutableStateOf<PrayerTimesData?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var upcomingPrayerPeriod by remember { mutableStateOf(PrayerPeriod.ISHA) }
    var showStars by remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.any { it }) {
            coroutineScope.launch {
                isLoading = true
                val location = getCurrentLocation(context)
                prayerData = if (location != null) {
                    val cityName = getCityNameFromCoordinates(context, location)
                    PrayerTimesData(
                        cityName = cityName,
                        times = mapOf(
                            PrayerPeriod.FAJR to "04:30",
                            PrayerPeriod.DHUHR to "11:50",
                            PrayerPeriod.ASR to "15:12",
                            PrayerPeriod.MAGHRIB to "17:45",
                            PrayerPeriod.ISHA to "18:57"
                        )
                    )
                } else null
                isLoading = false
            }
        } else {
            isLoading = false
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    LaunchedEffect(prayerData) {
        prayerData ?: return@LaunchedEffect
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

        snapshotFlow { Calendar.getInstance().timeInMillis / 60000 }
            .collectLatest {
                val nowCal = Calendar.getInstance()

                // ✅ Error handling saat parsing waktu
                val prayerCalendars = prayerData!!.times.mapValues { (_, timeStr) ->
                    runCatching {
                        Calendar.getInstance().apply {
                            time = timeFormatter.parse(timeStr) ?: Date()
                        }
                    }.getOrElse {
                        // Kalau gagal parse → fallback waktu sekarang
                        Calendar.getInstance()
                    }
                }

                // ✅ Error handling pemilihan periode
                val nextPeriod = try {
                    when {
                        nowCal.before(prayerCalendars.getValue(PrayerPeriod.ISHA)) &&
                                nowCal.after(prayerCalendars.getValue(PrayerPeriod.MAGHRIB)) -> PrayerPeriod.ISHA
                        nowCal.before(prayerCalendars.getValue(PrayerPeriod.MAGHRIB)) &&
                                nowCal.after(prayerCalendars.getValue(PrayerPeriod.ASR)) -> PrayerPeriod.MAGHRIB
                        nowCal.before(prayerCalendars.getValue(PrayerPeriod.ASR)) &&
                                nowCal.after(prayerCalendars.getValue(PrayerPeriod.DHUHR)) -> PrayerPeriod.ASR
                        nowCal.before(prayerCalendars.getValue(PrayerPeriod.DHUHR)) &&
                                nowCal.after(prayerCalendars.getValue(PrayerPeriod.FAJR)) -> PrayerPeriod.DHUHR
                        else -> PrayerPeriod.FAJR
                    }
                } catch (e: Exception) {
                    // fallback aman → default Subuh
                    PrayerPeriod.FAJR
                }

                upcomingPrayerPeriod = nextPeriod
                showStars = nextPeriod in listOf(
                    PrayerPeriod.MAGHRIB,
                    PrayerPeriod.ISHA,
                    PrayerPeriod.FAJR
                )
            }
    }


    val cardColor = when (upcomingPrayerPeriod) {
        PrayerPeriod.FAJR -> Color(0xFF637AB9)
        PrayerPeriod.DHUHR -> Color(0xFFA8FBD3)
        PrayerPeriod.ASR -> Color(0xFFEF7722)
        PrayerPeriod.MAGHRIB -> Color(0xFF637AB9)
        PrayerPeriod.ISHA -> Color(0xFF31326F)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrayerTimeHeader(
            cityName = if (isLoading) "Memuat..." else prayerData?.cityName ?: "Tidak Diketahui",
            onLocationClick = { /* TODO: pilih lokasi manual */ }
        )

        Spacer(Modifier.height(12.dp))

        when {
            isLoading && prayerData == null -> ShimmerPrayerTimeCard()
            prayerData != null -> PrayerTimeCardContent(
                prayerData = prayerData!!,
                upcomingPrayerPeriod = upcomingPrayerPeriod,
                cardColor = cardColor,
                showStars = showStars
            )
            else -> PrayerTimeStatusCard(statusText = "Izin lokasi ditolak atau GPS mati")
        }
    }
}
