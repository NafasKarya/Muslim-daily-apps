package com.nafaskarya.muslimdaily.components.widgets.guestUser

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nafaskarya.muslimdaily.components.shared.SearchPage
import com.nafaskarya.muslimdaily.ui.utils.Screen

/**
 * Konten aplikasi (NavHost) yang digunakan bersama oleh semua layout.
 */
@Composable
fun AppContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) { DashboardContent() }
        composable(Screen.Search.route) { SearchPage() }
        // bisa tambahkan route lain: Qibla, Tasbih
    }
}