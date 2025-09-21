package com.nafaskarya.muslimdaily.guest

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.nafaskarya.muslimdaily.components.widgets.data.guestDashboardNavItems
import com.nafaskarya.muslimdaily.components.widgets.guestUser.CompactScreenLayout
import com.nafaskarya.muslimdaily.components.widgets.guestUser.ExpandedScreenLayout

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuestDashboard() {
    val navController = rememberNavController()
    // State untuk melacak item mana yang dipilih
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navColor = Color(0xFF8B5A33)

    // Deteksi ukuran layar
    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity)

    // Logika navigasi menjadi lebih sederhana, menggunakan 'route' dari NavItem
    val onNavItemClick: (Int) -> Unit = { index ->
        selectedItemIndex = index
        val selectedRoute = guestDashboardNavItems[index].route

        navController.navigate(selectedRoute) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    // --- PEMILIHAN LAYOUT BERDASARKAN LEBAR LAYAR ---
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactScreenLayout(
                navController = navController,
                items = guestDashboardNavItems, // <-- Menggunakan list dari file baru
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick,
                navColor = navColor
            )
        }
        else -> {
            ExpandedScreenLayout(
                navController = navController,
                items = guestDashboardNavItems, // <-- Menggunakan list dari file baru
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onNavItemClick,
                navColor = navColor
            )
        }
    }
}