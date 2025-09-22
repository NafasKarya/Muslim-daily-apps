package com.nafaskarya.muslimdaily.components.widgets

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nafaskarya.muslimdaily.components.shared.shimmer.ShimmerPrayerTimeCard
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeCardContent
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeHeader
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeStatusCard // Pastikan import ini ada
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModel

@Composable
fun PrayerTimeCard(
    viewModel: PrayerTimeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.any { it }) {
            viewModel.refreshDataIfStale()
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

    // Tampilan header dan konten/shimmer/status
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
                    // --- PERBAIKAN UTAMA DI SINI ---
                    PrayerTimeCardContent(
                        prayerData = state.prayerData,
                        upcomingPrayerPeriod = state.upcomingPrayerPeriod,
                        imageRes = state.cardImage, // <-- Menggunakan imageRes, bukan cardColor
                        showStars = state.showStars,
                        gregorianDateText = state.formattedDate
                    )
                }
            }
        }
        is PrayerTimeUiState.Error -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrayerTimeHeader(cityName = "Gagal", onLocationClick = {})
                Spacer(Modifier.height(12.dp))
                PrayerTimeStatusCard(statusText = state.message)
            }
        }
    }
}