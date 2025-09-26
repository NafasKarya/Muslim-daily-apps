package com.nafaskarya.muslimdaily.guest

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.nafaskarya.muslimdaily.components.widgets.data.guestDashboardNavItems
import com.nafaskarya.muslimdaily.components.widgets.guestUser.CompactScreenLayout
import com.nafaskarya.muslimdaily.components.widgets.guestUser.ExpandedScreenLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuestDashboard() {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { guestDashboardNavItems.size }
    val selectedItemIndex by remember { derivedStateOf { pagerState.currentPage } }
    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity).widthSizeClass

    var isLoading by remember { mutableStateOf(true) }

    // --- PERUBAHAN 1: Buat fungsi refresh yang bisa dipanggil ulang ---
    val refreshDashboard: () -> Unit = {
        coroutineScope.launch {
            // Tampilkan shimmer
            isLoading = true
            // Simulasi proses ambil data (misal: 1.5 detik)
            delay(1500)
            // Sembunyikan shimmer
            isLoading = false
        }
    }

    // Panggil refresh saat pertama kali layar dimuat
    LaunchedEffect(Unit) {
        refreshDashboard()
    }

    val onNavItemClick: (Int) -> Unit = { index ->
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }

    when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactScreenLayout(
                modifier = Modifier,
                windowSizeClass = windowSizeClass,
                pagerState = pagerState,
                items = guestDashboardNavItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick,
                isLoading = isLoading,
                // --- PERUBAHAN 2: Kirim fungsi refresh ke bawah ---
                onRefresh = refreshDashboard,
                onShowSnackbar = { /* tidak ada aksi */ }
            )
        }
        else -> { // Medium & Expanded
            ExpandedScreenLayout(
                modifier = Modifier,
                windowSizeClass = windowSizeClass,
                pagerState = pagerState,
                items = guestDashboardNavItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick,
                isLoading = isLoading,
                // --- PERUBAHAN 2: Kirim fungsi refresh ke bawah ---
                onRefresh = refreshDashboard,
                onShowSnackbar = { /* tidak ada aksi */ }
            )
        }
    }
}