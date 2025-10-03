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
import com.nafaskarya.muslimdaily.components.widgets.MenuItem
import com.nafaskarya.muslimdaily.components.widgets.ResponsiveAppNavigation
import com.nafaskarya.muslimdaily.components.widgets.dashboard.DashboardScreen
import com.nafaskarya.muslimdaily.components.widgets.data.NavItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompactScreenLayout(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowWidthSizeClass,
    pagerState: PagerState,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    onShowSnackbar: (String) -> Unit,
    onMenuItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                when (pageIndex) {
                    // --- PERBAIKAN: Panggil DashboardScreen, bukan DashboardContent ---
                    0 -> DashboardScreen(
                        isLoading = false, // isLoading kini dikelola di dalam DashboardScreen
                        onMenuItemClick = onMenuItemClick,
                        onShowSnackbar = onShowSnackbar
                    )
                    1 -> SearchPage()
                    // ... halaman lain
                }
            }
        }
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
    modifier: Modifier = Modifier,
    windowSizeClass: WindowWidthSizeClass,
    pagerState: PagerState,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    onShowSnackbar: (String) -> Unit,
    onMenuItemClick: (MenuItem) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ResponsiveAppNavigation(
            windowSizeClass = windowSizeClass,
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = onItemSelected
        )
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                when (pageIndex) {
                    // --- PERBAIKAN: Panggil DashboardScreen, bukan DashboardContent ---
                    0 -> DashboardScreen(
                        isLoading = false, // isLoading kini dikelola di dalam DashboardScreen
                        onMenuItemClick = onMenuItemClick,
                        onShowSnackbar = onShowSnackbar
                    )
                    1 -> SearchPage()
                    // ... halaman lain
                }
            }
        }
    }
}