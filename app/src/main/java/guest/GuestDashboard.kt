package com.nafaskarya.muslimdaily.guest

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.nafaskarya.muslimdaily.components.widgets.data.guestDashboardNavItems
import com.nafaskarya.muslimdaily.components.widgets.guestUser.CompactScreenLayout
import com.nafaskarya.muslimdaily.components.widgets.guestUser.ExpandedScreenLayout
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuestDashboard() {
    val coroutineScope = rememberCoroutineScope()
    // val navColor = Color(0xFF8B5A33) // <-- DIHAPUS: Warna sebaiknya diatur dari Theme

    // --- SETUP PAGERSTATE ---
    val pagerState = rememberPagerState {
        guestDashboardNavItems.size
    }
    // State untuk melacak item yang dipilih, sekarang dikontrol oleh Pager
    val selectedItemIndex by remember { derivedStateOf { pagerState.currentPage } }
    // ------------------------------------------

    // Deteksi ukuran layar
    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity).widthSizeClass // Langsung ambil width class

    // Logika klik item navigasi sekarang mengontrol Pager
    val onNavItemClick: (Int) -> Unit = { index ->
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }

    // --- PEMILIHAN LAYOUT BERDASARKAN LEBAR LAYAR ---
    when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactScreenLayout(
                // PERUBAHAN 1: Meneruskan windowSizeClass
                windowSizeClass = windowSizeClass,
                pagerState = pagerState,
                items = guestDashboardNavItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick
                // PERUBAHAN 2: Parameter navColor dihapus
            )
        }
        else -> { // Medium & Expanded
            ExpandedScreenLayout(
                // PERUBAHAN 1: Meneruskan windowSizeClass
                windowSizeClass = windowSizeClass,
                pagerState = pagerState,
                items = guestDashboardNavItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick
                // PERUBAHAN 2: Parameter navColor dihapus
            )
        }
    }
}