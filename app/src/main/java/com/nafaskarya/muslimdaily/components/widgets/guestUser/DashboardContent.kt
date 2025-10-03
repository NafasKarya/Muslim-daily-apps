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
import com.nafaskarya.muslimdaily.components.items.menuItems
import com.nafaskarya.muslimdaily.components.shared.refresh.RefreshableContent
import com.nafaskarya.muslimdaily.components.widgets.*
import com.nafaskarya.muslimdaily.components.widgets.dashboard.DashboardHeader
import com.nafaskarya.muslimdaily.components.widgets.dashboard.TopAppBarWhenScrolled
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.Dimens
import com.nafaskarya.muslimdaily.ui.data.hijri.HijriDate
import com.nafaskarya.muslimdaily.ui.utils.state.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.utils.state.UiState

sealed class DashboardUiAction {
    data object RequestLocationPermission : DashboardUiAction()
    data object FindMosqueClicked : DashboardUiAction()
    data class ShowSnackbar(val message: String) : DashboardUiAction()
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    isLoading: Boolean,
    prayerTimeState: PrayerTimeUiState,
    hijriState: UiState<HijriDate>,
    countdown: String,
    onRefresh: () -> Unit,
    onMenuItemClick: (MenuItem) -> Unit,
    onAction: (DashboardUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val headerHeightPx = with(LocalDensity.current) { Dimens.DashboardHeaderHeight.toPx() }
    val showTopBar by remember { derivedStateOf { scrollState.value > headerHeightPx / 2 } }

    val isRefreshing = prayerTimeState is PrayerTimeUiState.Loading || (prayerTimeState is PrayerTimeUiState.Success && prayerTimeState.isRefreshing)

    RefreshableContent(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
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
                prayerTimeState = prayerTimeState,
                hijriState = hijriState,
                countdown = countdown,
                onShowSnackbar = { message -> onAction(DashboardUiAction.ShowSnackbar(message)) },
                onRequestPermissionClick = { onAction(DashboardUiAction.RequestLocationPermission) }
            )

            Spacer(modifier = Modifier.height(Dimens.PaddingNormal))
            FindMosqueButton(onClick = { onAction(DashboardUiAction.FindMosqueClicked) })
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            // --- PERBAIKAN DI SINI: Hapus parameter `items` ---
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