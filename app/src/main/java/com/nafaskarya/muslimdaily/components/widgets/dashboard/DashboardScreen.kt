package com.nafaskarya.muslimdaily.components.widgets.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.nafaskarya.muslimdaily.components.widgets.MenuItem
import com.nafaskarya.muslimdaily.components.widgets.guestUser.DashboardContent
import com.nafaskarya.muslimdaily.components.widgets.guestUser.DashboardUiAction
import com.nafaskarya.muslimdaily.ui.repository.hijri.HijriRepository
import com.nafaskarya.muslimdaily.ui.repository.prayer_time.PrayerTimeRepository
import com.nafaskarya.muslimdaily.ui.utils.network.RetrofitClient
import com.nafaskarya.muslimdaily.ui.viewmodel.HijriViewModel
import com.nafaskarya.muslimdaily.ui.viewmodel.HijriViewModelFactory
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModel
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModelFactory
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    isLoading: Boolean,
    onMenuItemClick: (MenuItem) -> Unit,
    onShowSnackbar: (String) -> Unit
) {
    // Inisialisasi ViewModel tidak berubah
    val prayerTimeViewModel: PrayerTimeViewModel = viewModel(
        factory = PrayerTimeViewModelFactory(PrayerTimeRepository(RetrofitClient.prayerTimeApiService))
    )
    val hijriViewModel: HijriViewModel = viewModel(
        factory = HijriViewModelFactory(HijriRepository(RetrofitClient.hijriApiService))
    )

    // Pengumpulan state tidak berubah
    val prayerTimeState by prayerTimeViewModel.uiState.collectAsStateWithLifecycle()
    val hijriState by hijriViewModel.hijriDateState.collectAsStateWithLifecycle()
    val countdown by prayerTimeViewModel.countdownState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var permissionClickCount by rememberSaveable { mutableIntStateOf(0) }

    // --- PERBAIKAN 1: Deklarasikan tipe lambda secara eksplisit ---
    val refreshDataWithLocation: () -> Unit = {
        try {
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        Timber.d("Lokasi baru: Lat=${location.latitude}, Lon=${location.longitude}")
                        prayerTimeViewModel.refreshData(location.latitude, location.longitude)
                        // --- PERBAIKAN 2: Gunakan nama fungsi yang benar ---
                        hijriViewModel.fetchHijriDate() // Ganti dari getHijriDate()
                    } else {
                        onShowSnackbar("Gagal mendapatkan lokasi. Pastikan GPS aktif.")
                    }
                }.addOnFailureListener { e ->
                    onShowSnackbar("Error lokasi: ${e.message}")
                }
        } catch (e: SecurityException) {
            onShowSnackbar("Izin lokasi diperlukan.")
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)) {
            refreshDataWithLocation()
        } else {
            onShowSnackbar("Izin lokasi ditolak.")
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            refreshDataWithLocation()
        }
    }

    // Pemanggilan DashboardContent tidak berubah
    DashboardContent(
        isLoading = isLoading,
        prayerTimeState = prayerTimeState,
        hijriState = hijriState,
        countdown = countdown,
        onRefresh = refreshDataWithLocation,
        onMenuItemClick = onMenuItemClick,
        onAction = { action ->
            when (action) {
                is DashboardUiAction.RequestLocationPermission -> {
                    permissionClickCount++
                    if (permissionClickCount > 3) {
                        onShowSnackbar("Aktifkan izin dari Pengaturan ponsel.")
                    } else {
                        locationPermissionLauncher.launch(
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                        )
                    }
                }
                is DashboardUiAction.ShowSnackbar -> onShowSnackbar(action.message)
                is DashboardUiAction.FindMosqueClicked -> { /* TODO: Handle navigasi ke peta */ }
            }
        }
    )
}