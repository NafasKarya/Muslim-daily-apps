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
import kotlinx.coroutines.launch

// Anotasi @RequiresApi sudah dihapus
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuestDashboard() {
    // Logika Snackbar dihilangkan karena tidak ada Scaffold
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState { guestDashboardNavItems.size }
    val selectedItemIndex by remember { derivedStateOf { pagerState.currentPage } }

    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity).widthSizeClass

    val onNavItemClick: (Int) -> Unit = { index ->
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }

    // --- PEMILIHAN LAYOUT BERDASARKAN LEBAR LAYAR ---
    // Langsung panggil 'when' block tanpa Scaffold
    when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactScreenLayout(
                modifier = Modifier, // innerPadding tidak ada lagi
                windowSizeClass = windowSizeClass,
                pagerState = pagerState,
                items = guestDashboardNavItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick,
                // onShowSnackbar harus tetap ada, tapi tidak akan melakukan apa-apa
                onShowSnackbar = { /* tidak ada aksi */ }
            )
        }
        else -> { // Medium & Expanded
            ExpandedScreenLayout(
                modifier = Modifier, // innerPadding tidak ada lagi
                windowSizeClass = windowSizeClass,
                pagerState = pagerState,
                items = guestDashboardNavItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick,
                // onShowSnackbar harus tetap ada, tapi tidak akan melakukan apa-apa
                onShowSnackbar = { /* tidak ada aksi */ }
            )
        }
    }
}