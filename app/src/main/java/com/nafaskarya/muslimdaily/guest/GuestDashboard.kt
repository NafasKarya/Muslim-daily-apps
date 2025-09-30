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
import com.nafaskarya.muslimdaily.components.widgets.MenuItem // Pastikan MenuItem di-import
import com.nafaskarya.muslimdaily.components.widgets.data.guestDashboardNavItems
import com.nafaskarya.muslimdaily.components.widgets.guestUser.CompactScreenLayout
import com.nafaskarya.muslimdaily.components.widgets.guestUser.ExpandedScreenLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuestDashboard(
    // --- PERUBAHAN 1: Terima NavController untuk navigasi ---
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { guestDashboardNavItems.size }
    val selectedItemIndex by remember { derivedStateOf { pagerState.currentPage } }
    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity).widthSizeClass

    var isLoading by remember { mutableStateOf(true) }

    val refreshDashboard: () -> Unit = {
        coroutineScope.launch {
            isLoading = true
            delay(1500)
            isLoading = false
        }
    }

    LaunchedEffect(Unit) {
        refreshDashboard()
    }

    val onNavItemClick: (Int) -> Unit = { index ->
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }

    // --- PERUBAHAN 2: Definisikan aksi klik menu di sini ---
    val onMenuItemClick: (MenuItem) -> Unit = { menuItem ->
        when (menuItem.title) {
            "Quran" -> navController.navigate("quran_route")
            "Adzan" -> navController.navigate("adzan_route")
            "Qibla" -> navController.navigate("qibla_route")
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
                isLoading = isLoading,
                onRefresh = refreshDashboard,
                onShowSnackbar = { /* tidak ada aksi */ },
                // --- PERUBAHAN 3: Kirim aksi klik ke bawah ---
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
                isLoading = isLoading,
                onRefresh = refreshDashboard,
                onShowSnackbar = { /* tidak ada aksi */ },
                // --- PERUBAHAN 3: Kirim aksi klik ke bawah ---
                onMenuItemClick = onMenuItemClick
            )
        }
    }
}