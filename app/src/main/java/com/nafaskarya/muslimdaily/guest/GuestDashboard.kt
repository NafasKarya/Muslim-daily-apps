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
import androidx.navigation.NavController
import com.nafaskarya.muslimdaily.components.widgets.MenuItem
import com.nafaskarya.muslimdaily.components.widgets.data.guestDashboardNavItems
import com.nafaskarya.muslimdaily.components.widgets.guestUser.CompactScreenLayout
import com.nafaskarya.muslimdaily.components.widgets.guestUser.ExpandedScreenLayout
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuestDashboard(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { guestDashboardNavItems.size }
    val selectedItemIndex by remember { derivedStateOf { pagerState.currentPage } }
    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity).widthSizeClass

    // --- DIHAPUS ---
    // Logika isLoading dan onRefresh tidak lagi diperlukan di sini.
    // DashboardScreen akan menanganinya secara internal.
    // var isLoading by remember { mutableStateOf(true) }
    // val refreshDashboard: () -> Unit = { ... }
    // LaunchedEffect(Unit) { ... }
    // ---------------

    val onNavItemClick: (Int) -> Unit = { index ->
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }

    val onMenuItemClick: (MenuItem) -> Unit = { menuItem ->
        when (menuItem.title) {
            "Quran" -> navController.navigate("quran_route")
            "Kitab Literal" -> navController.navigate("kitab_route")
            "Qibla" -> navController.navigate("qibla_route")
            "Pengaturan" -> navController.navigate("settings_route")
            // Tambahkan navigasi untuk item lain jika perlu
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
                // --- PERBAIKAN: Hapus parameter `isLoading` dan `onRefresh` ---
                onShowSnackbar = { /* TODO: Implementasi Snackbar */ },
                onMenuItemClick = onMenuItemClick
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
                // --- PERBAIKAN: Hapus parameter `isLoading` dan `onRefresh` ---
                onShowSnackbar = { /* TODO: Implementasi Snackbar */ },
                onMenuItemClick = onMenuItemClick
            )
        }
    }
}