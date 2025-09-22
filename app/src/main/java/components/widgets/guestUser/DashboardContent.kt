// File: com/nafaskarya/muslimdaily/components/widgets/guestUser/DashboardContent.kt

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
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    prayerTimeViewModel: PrayerTimeViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    // Menggunakan Dimens untuk konsistensi dengan tinggi header
    val headerHeightPx = with(LocalDensity.current) { Dimens.DashboardHeaderHeight.toPx() }
    val showTopBar by remember {
        derivedStateOf {
            scrollState.value > headerHeightPx / 2
        }
    }

    val uiState by prayerTimeViewModel.uiState.collectAsStateWithLifecycle()

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
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge)) // Menggunakan Dimens
            Text(
                text = Strings.PrayerTimeTitle,
                modifier = Modifier.padding(horizontal = Dimens.PaddingNormal) // Menggunakan Dimens
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingMedium)) // Menggunakan Dimens
            PrayerTimeCard(viewModel = prayerTimeViewModel)
            Spacer(modifier = Modifier.height(Dimens.PaddingNormal)) // Menggunakan Dimens
            FindMosqueButton(onClick = { /* ... */ })
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge)) // Menggunakan Dimens
            MenuGrid()
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge)) // Menggunakan Dimens
            NgajiOnlineSection()
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge)) // Menggunakan Dimens
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

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun GuestDashboardPreview() {
    // GuestDashboard()
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun GuestDashboardTabletPreview() {
    // GuestDashboard()
}