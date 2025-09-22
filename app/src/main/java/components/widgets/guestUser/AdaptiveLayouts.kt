// File: com/nafaskarya/muslimdaily/components/widgets/guestUser/ScreenLayouts.kt

package com.nafaskarya.muslimdaily.components.widgets.guestUser

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nafaskarya.muslimdaily.components.shared.SearchPage
import com.nafaskarya.muslimdaily.components.widgets.ResponsiveAppNavigation
import com.nafaskarya.muslimdaily.components.widgets.data.NavItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompactScreenLayout(
    windowSizeClass: WindowWidthSizeClass,
    pagerState: PagerState,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Konten utama (Pager)
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> DashboardContent()
                    1 -> SearchPage()
                    // ... tambahkan halaman lain di sini
                }
            }
        }

        // PERBAIKAN: Memanggil ResponsiveAppNavigation, bukan CustomBottomBar
        ResponsiveAppNavigation(
            windowSizeClass = windowSizeClass,
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = onItemSelected
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandedScreenLayout(
    windowSizeClass: WindowWidthSizeClass,
    pagerState: PagerState,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // PERBAIKAN: Menampilkan NavigationRail di samping
        ResponsiveAppNavigation(
            windowSizeClass = windowSizeClass,
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = onItemSelected
        )

        // Konten utama (Pager)
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> DashboardContent()
                    1 -> SearchPage()
                    // ... tambahkan halaman lain di sini
                }
            }
        }
    }
}