package com.nafaskarya.muslimdaily.components.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.components.shared.shimmer.prayer.ShimmerPrayerTimeCard
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.NewPrayerTimeCardUI
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeHeader
import com.nafaskarya.muslimdaily.ui.data.hijri.HijriDate
import com.nafaskarya.muslimdaily.ui.utils.state.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.utils.state.UiState

/**
 * A stateless composable that displays the prayer time UI based on the given states.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimeCard(
    // --- TERIMA STATE, BUKAN VIEWMODEL ---
    prayerTimeState: PrayerTimeUiState,
    hijriState: UiState<HijriDate>,
    countdown: String,
    // ------------------------------------
    onShowSnackbar: (message: String) -> Unit,
    onRequestPermissionClick: () -> Unit
) {
    // --- HAPUS SEMUA `collectAsStateWithLifecycle` DARI SINI ---

    when (val state = prayerTimeState) {
        is PrayerTimeUiState.Loading -> {
            ShimmerPrayerTimeCard()
        }
        is PrayerTimeUiState.Success -> {
            if (state.isRefreshing) {
                ShimmerPrayerTimeCard()
            } else {
                NewPrayerTimeCardUI(
                    state = state,
                    hijriState = hijriState,
                    countdown = countdown
                )
            }
        }
        is PrayerTimeUiState.Error -> {
            // UI untuk handle state error, termasuk izin lokasi
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrayerTimeHeader(cityName = "Lokasi Tidak Tersedia", onLocationClick = {})
                Spacer(Modifier.height(12.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(horizontal = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_rejected_location),
                            contentDescription = "Background Ilustrasi Izin Lokasi",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Izin Lokasi Diperlukan",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Aktifkan lokasi untuk menampilkan jadwal sholat akurat di area Anda.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.8f),
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(24.dp))
                            Button(
                                onClick = onRequestPermissionClick,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {
                                Text("Aktifkan Lokasi")
                            }
                        }
                    }
                }
            }
        }
    }
}