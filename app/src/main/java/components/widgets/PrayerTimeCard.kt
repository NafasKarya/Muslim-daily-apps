package com.nafaskarya.muslimdaily.components.widgets

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nafaskarya.muslimdaily.R // <-- Pastikan Anda mengimpor R dari paket Anda
import com.nafaskarya.muslimdaily.components.shared.shimmer.ShimmerPrayerTimeCard
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeCardContent
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeHeader
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeStatusCard
import ui.viewmodel.PrayerTimeUiState
import ui.viewmodel.PrayerTimeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimeCard(
    viewModel: PrayerTimeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Launcher ini tetap dibutuhkan jika Anda ingin memberi kesempatan pengguna
    // untuk memberikan izin di masa depan, meskipun tombolnya sudah tidak ada.
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.any { it }) {
            viewModel.refreshData()
        }
    }

    // Tampilan berdasarkan state dari ViewModel
    when (val state = uiState) {
        is PrayerTimeUiState.Loading -> {
            ShimmerPrayerTimeCard()
        }
        is PrayerTimeUiState.Success -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrayerTimeHeader(
                    cityName = state.prayerData.cityName,
                    onLocationClick = { /* TODO */ }
                )
                Spacer(Modifier.height(12.dp))

                if (state.isRefreshing) {
                    ShimmerPrayerTimeCard()
                } else {
                    PrayerTimeCardContent(
                        prayerData = state.prayerData,
                        upcomingPrayerPeriod = state.upcomingPrayerPeriod,
                        imageRes = state.cardImage,
                        showStars = state.showStars,
                        gregorianDateText = state.formattedDate
                    )
                }
            }
        }
        is PrayerTimeUiState.Error -> {
            val isPermissionError = state.message.contains("Izin lokasi", ignoreCase = true)

            // --- PERUBAHAN UTAMA DI SINI ---
            if (isPermissionError) {
                // Jika error karena izin ditolak, tampilkan gambar saja
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp), // Beri sedikit padding vertikal
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_rejected_location), // GANTI DENGAN GAMBAR ANDA
                        contentDescription = "Ilustrasi izin lokasi ditolak"
                    )
                }
            } else {
                // Jika error karena hal lain (misal: jaringan), tampilkan pesan seperti biasa
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PrayerTimeHeader(cityName = "Gagal Memuat", onLocationClick = {})
                    Spacer(Modifier.height(12.dp))
                    PrayerTimeStatusCard(statusText = state.message)
                }
            }
            // ------------------------------------
        }
    }
}

