package com.nafaskarya.muslimdaily.components.widgets.guestUser

import NewestCard
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.nafaskarya.muslimdaily.components.shared.refresh.RefreshableContent
import com.nafaskarya.muslimdaily.components.widgets.*
import com.nafaskarya.muslimdaily.components.widgets.dashboard.DashboardHeader
import com.nafaskarya.muslimdaily.components.widgets.dashboard.TopAppBarWhenScrolled
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.Dimens
import com.nafaskarya.muslimdaily.ui.repository.hijri.HijriRepository
import com.nafaskarya.muslimdaily.ui.repository.prayer_time.PrayerTimeRepository
import com.nafaskarya.muslimdaily.ui.utils.network.RetrofitClient
import com.nafaskarya.muslimdaily.ui.utils.state.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.viewmodel.HijriViewModel
import com.nafaskarya.muslimdaily.ui.viewmodel.HijriViewModelFactory
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModel
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModelFactory
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    prayerTimeViewModel: PrayerTimeViewModel = viewModel(
        factory = PrayerTimeViewModelFactory(
            PrayerTimeRepository(RetrofitClient.prayerTimeApiService)
        )
    ),
    hijriViewModel: HijriViewModel = viewModel(
        factory = HijriViewModelFactory(
            HijriRepository(RetrofitClient.hijriApiService)
        )
    ),
    onShowSnackbar: (String) -> Unit,
    onMenuItemClick: (MenuItem) -> Unit
) {
    val scrollState = rememberScrollState()
    val headerHeightPx = with(LocalDensity.current) { Dimens.DashboardHeaderHeight.toPx() }
    val showTopBar by remember { derivedStateOf { scrollState.value > headerHeightPx / 2 } }

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var permissionClickCount by rememberSaveable { mutableStateOf(0) }

    val uiState by prayerTimeViewModel.uiState.collectAsStateWithLifecycle()

    // --- FUNGSI INI SEPENUHNYA DIPERBAIKI ---
    val refreshDataWithLocation = {
        try {
            // Menggunakan getCurrentLocation untuk memaksa pengambilan lokasi baru.
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location ->
                if (location != null) {
                    // Log untuk memastikan kita mendapatkan koordinat yang benar
                    Timber.d("Lokasi baru didapatkan: Lat=${location.latitude}, Lon=${location.longitude}")
                    prayerTimeViewModel.refreshData(location.latitude, location.longitude)
                    hijriViewModel.fetchHijriDate()
                    onRefresh()
                } else {
                    Timber.w("Gagal mendapatkan lokasi baru (hasil null).")
                    onShowSnackbar("Gagal mendapatkan lokasi. Pastikan GPS aktif dan coba lagi.")
                }
            }.addOnFailureListener { exception ->
                Timber.e(exception, "Error saat meminta lokasi baru.")
                onShowSnackbar("Error lokasi: ${exception.message}")
            }
        } catch (e: SecurityException) {
            Timber.e(e, "Izin lokasi tidak diberikan.")
            onShowSnackbar("Izin lokasi diperlukan untuk fitur ini.")
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)) {
            refreshDataWithLocation()
        } else {
            onShowSnackbar("Izin lokasi ditolak.")
        }
    }

    LaunchedEffect(Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            refreshDataWithLocation()
        }
    }

    val isRefreshingPrayerTime = when (val state = uiState) {
        is PrayerTimeUiState.Success -> state.isRefreshing
        is PrayerTimeUiState.Loading -> true
        else -> false
    }

    RefreshableContent(
        isRefreshing = isRefreshingPrayerTime,
        onRefresh = {
            refreshDataWithLocation()
        },
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            DashboardHeader(isLoading = isLoading)
            LastReadCard(isLoading = isLoading)
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            Text(
                text = Strings.PrayerTimeTitle,
                modifier = Modifier.padding(horizontal = Dimens.PaddingNormal)
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

            PrayerTimeCard(
                prayerTimeViewModel = prayerTimeViewModel,
                hijriViewModel = hijriViewModel,
                onShowSnackbar = onShowSnackbar,
                onRequestPermissionClick = {
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
                }
            )

            Spacer(modifier = Modifier.height(Dimens.PaddingNormal))
            FindMosqueButton(onClick = { /* ... */ })
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            MenuGrid(onMenuItemClick = onMenuItemClick)
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            NgajiOnlineSection()
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            NewestCard()
        }

        AnimatedVisibility(
            visible = showTopBar,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            TopAppBarWhenScrolled()
        }
    }
}


// --- Kode Preview tidak berubah ---
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 360, heightDp = 800, name = "Dashboard Loaded")
@Composable
fun GuestDashboardPreview() {
    DashboardContent(
        isLoading = false,
        onRefresh = {},
        onShowSnackbar = {},
        onMenuItemClick = {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 360, heightDp = 800, name = "Dashboard Loading")
@Composable
fun GuestDashboardLoadingPreview() {
    DashboardContent(
        isLoading = true,
        onRefresh = {},
        onShowSnackbar = {},
        onMenuItemClick = {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 1200, heightDp = 800, name = "Tablet Dashboard")
@Composable
fun GuestDashboardTabletPreview() {
    DashboardContent(
        isLoading = false,
        onRefresh = {},
        onShowSnackbar = {},
        onMenuItemClick = {}
    )
}