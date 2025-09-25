package com.nafaskarya.muslimdaily.components.widgets

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.components.shared.shimmer.ShimmerPrayerTimeCard
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.NewPrayerTimeCardUI
import com.nafaskarya.muslimdaily.components.widgets.prayerTime.PrayerTimeHeader
import ui.viewmodel.PrayerTimeUiState
import ui.viewmodel.PrayerTimeViewModel

@Composable
fun PrayerTimeCard(
    viewModel: PrayerTimeViewModel = viewModel(),
    onShowSnackbar: (message: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var permissionClickCount by rememberSaveable { mutableStateOf(0) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)
        ) {
            viewModel.refreshData()
            permissionClickCount = 0
        }
    }

    when (val state = uiState) {
        is PrayerTimeUiState.Loading -> {
            ShimmerPrayerTimeCard()
        }
        is PrayerTimeUiState.Success -> {
            // --- PERUBAHAN UTAMA DI SINI ---
            // Tambahkan kondisi untuk memeriksa state.isRefreshing
            if (state.isRefreshing) {
                ShimmerPrayerTimeCard()
            } else {
                NewPrayerTimeCardUI(state = state)
            }
        }
        is PrayerTimeUiState.Error -> {
            val isPermissionError = state.message.contains("Izin lokasi", ignoreCase = true)
            if (isPermissionError) {
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
                                    onClick = {
                                        permissionClickCount++
                                        if (permissionClickCount > 3) {
                                            onShowSnackbar("Anda sudah mencoba terlalu sering. Aktifkan izin dari Pengaturan ponsel.")
                                        } else {
                                            locationPermissionLauncher.launch(
                                                arrayOf(
                                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                                )
                                            )
                                        }
                                    },
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
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PrayerTimeHeader(cityName = "Gagal Memuat", onLocationClick = {})
                    Spacer(Modifier.height(12.dp))
                    PrayerTimeStatusCard(statusText = state.message)
                }
            }
        }
    }
}