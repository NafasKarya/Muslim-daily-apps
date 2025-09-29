package com.nafaskarya.muslimdaily.components.widgets.guestUser

import NewestCard
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nafaskarya.muslimdaily.components.shared.refresh.RefreshableContent
import com.nafaskarya.muslimdaily.components.widgets.*
import com.nafaskarya.muslimdaily.components.widgets.dashboard.DashboardHeader
import com.nafaskarya.muslimdaily.components.widgets.dashboard.TopAppBarWhenScrolled
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.Dimens
import ui.utils.state.PrayerTimeUiState
import ui.viewmodel.PrayerTimeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    prayerTimeViewModel: PrayerTimeViewModel = viewModel(),
    onShowSnackbar: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val headerHeightPx = with(LocalDensity.current) { Dimens.DashboardHeaderHeight.toPx() }

    val showTopBar by remember {
        derivedStateOf {
            scrollState.value > headerHeightPx / 2
        }
    }

    val uiState by prayerTimeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is PrayerTimeUiState.Error) {
            val errorMessage = (uiState as PrayerTimeUiState.Error).message
            if (!errorMessage.contains("Izin lokasi", ignoreCase = true)) {
                onShowSnackbar(errorMessage)
            }
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
            prayerTimeViewModel.refreshData()
            onRefresh()
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

            // --- PERBAIKAN DI SINI ---
            // Nama parameter diubah dari 'viewModel' menjadi 'prayerTimeViewModel'
            PrayerTimeCard(
                prayerTimeViewModel = prayerTimeViewModel,
                onShowSnackbar = onShowSnackbar
            )
            // -------------------------

            Spacer(modifier = Modifier.height(Dimens.PaddingNormal))
            FindMosqueButton(onClick = { /* ... */ })
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            MenuGrid()
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


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 360, heightDp = 800, name = "Dashboard Loaded")
@Composable
fun GuestDashboardPreview() {
    DashboardContent(
        isLoading = false,
        onRefresh = {},
        onShowSnackbar = {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 360, heightDp = 800, name = "Dashboard Loading")
@Composable
fun GuestDashboardLoadingPreview() {
    DashboardContent(
        isLoading = true,
        onRefresh = {},
        onShowSnackbar = {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 1200, heightDp = 800, name = "Tablet Dashboard")
@Composable
fun GuestDashboardTabletPreview() {
    DashboardContent(
        isLoading = false,
        onRefresh = {},
        onShowSnackbar = {}
    )
}
