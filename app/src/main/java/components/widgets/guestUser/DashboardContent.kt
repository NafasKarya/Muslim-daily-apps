package com.nafaskarya.muslimdaily.components.widgets.guestUser

import NewestCard
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
import ui.viewmodel.PrayerTimeUiState
import ui.viewmodel.PrayerTimeViewModel

// Anotasi @RequiresApi sudah dihapus
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
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

    val isRefreshing = when (val state = uiState) {
        is PrayerTimeUiState.Success -> state.isRefreshing
        is PrayerTimeUiState.Loading -> true
        else -> false
    }

    RefreshableContent(
        isRefreshing = isRefreshing,
        onRefresh = { prayerTimeViewModel.refreshData() },
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            DashboardHeader()
            LastReadCard()
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            Text(
                text = Strings.PrayerTimeTitle,
                modifier = Modifier.padding(horizontal = Dimens.PaddingNormal)
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

            PrayerTimeCard(
                viewModel = prayerTimeViewModel,
                onShowSnackbar = onShowSnackbar
            )

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


// Anotasi @RequiresApi sudah dihapus
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun GuestDashboardPreview() {
    DashboardContent(onShowSnackbar = { /* preview tidak melakukan apa-apa */ })
}

// Anotasi @RequiresApi sudah dihapus
@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun GuestDashboardTabletPreview() {
    DashboardContent(onShowSnackbar = { /* preview tidak melakukan apa-apa */ })
}