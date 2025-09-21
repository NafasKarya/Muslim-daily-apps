package com.nafaskarya.muslimdaily.components.widgets.guestUser

import NewestCard
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nafaskarya.muslimdaily.components.widgets.*
import com.nafaskarya.muslimdaily.guest.GuestDashboard
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons
import com.nafaskarya.muslimdaily.layouts.theme.Dimens
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeUiState
import com.nafaskarya.muslimdaily.ui.viewmodel.PrayerTimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    prayerTimeViewModel: PrayerTimeViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val headerHeightPx = with(LocalDensity.current) { 150.dp.toPx() }
    val showTopBar by remember {
        derivedStateOf {
            scrollState.value > headerHeightPx / 2
        }
    }

    // --- LOGIKA PULL-TO-REFRESH DIMODIFIKASI ---
    val uiState by prayerTimeViewModel.uiState.collectAsStateWithLifecycle()
    val pullToRefreshState = rememberPullToRefreshState()

    // Baris ini tidak lagi diperlukan karena isRefreshing sudah ada di dalam uiState
    // val isRefreshing by prayerTimeViewModel.isRefreshing.collectAsStateWithLifecycle()

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            prayerTimeViewModel.refreshData()
        }
    }

    // Logika untuk menghentikan animasi refresh sekarang membaca dari uiState
    LaunchedEffect(uiState) {
        if (uiState is PrayerTimeUiState.Success && !(uiState as PrayerTimeUiState.Success).isRefreshing) {
            pullToRefreshState.endRefresh()
        } else if (uiState is PrayerTimeUiState.Error) {
            // Hentikan juga jika terjadi error
            pullToRefreshState.endRefresh()
        }
    }
    // -------------------------------------------

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            DashboardHeader()
            LastReadCard()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = Strings.PrayerTimeTitle,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            PrayerTimeCard(viewModel = prayerTimeViewModel)
            Spacer(modifier = Modifier.height(16.dp))
            FindMosqueButton(onClick = { /* ... */ })
            Spacer(modifier = Modifier.height(24.dp))
            MenuGrid()
            Spacer(modifier = Modifier.height(24.dp))
            NgajiOnlineSection()
            Spacer(modifier = Modifier.height(24.dp))
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

        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

// ... (Composable lainnya tidak berubah) ...
@Composable
private fun TopAppBarWhenScrolled() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Muslim Daily",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DashboardHeader() {
    val gradientStart = Color(0xFFE0FFE0)
    val gradientEnd = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(gradientStart, gradientEnd)
                )
            )
            .padding(horizontal = Dimens.PaddingLarge, vertical = Dimens.PaddingXLarge)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Notification */ }) {
                Icon(
                    imageVector = AppIcons.Notification,
                    contentDescription = "Notifications",
                    tint = Color.Black,
                    modifier = Modifier.size(Dimens.IconMedium)
                )
            }
            Spacer(modifier = Modifier.width(Dimens.PaddingMedium))
            IconButton(onClick = { /* Menu */ }) {
                Icon(
                    imageVector = AppIcons.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(Dimens.IconMedium)
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text(
                text = Strings.Greeting,
                fontSize = Dimens.TextXLarge,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
            Text(
                text = Strings.UserName,
                fontSize = Dimens.TextXXLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
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