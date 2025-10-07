package com.nafaskarya.muslimdaily.components.widgets.guestUser // Pastikan package ini benar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nafaskarya.muslimdaily.components.shared.SearchPage
import com.nafaskarya.muslimdaily.components.widgets.dashboard.DashboardScreen
import com.nafaskarya.muslimdaily.ui.utils.Screen

/**
 * Konten aplikasi (NavHost) yang digunakan bersama oleh semua layout.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent(
    navController: NavHostController,
    isLoading: Boolean,
    // onRefresh tidak lagi diperlukan di sini, karena DashboardScreen menanganinya
    onShowSnackbar: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            // --- PERBAIKAN: Panggil DashboardScreen, BUKAN DashboardContent ---
            DashboardScreen(
                isLoading = isLoading,
                onShowSnackbar = onShowSnackbar,
                onMenuItemClick = { menuItem ->
                    // Logika navigasi saat item menu di-klik
                    when (menuItem.title) {
                        "Quran" -> navController.navigate("quran_route")
                        "Kitab Literal" -> navController.navigate("kitab_route")
                        "Podcast Islami" -> navController.navigate("podcast_route")
                        "Pengaturan" -> navController.navigate("settings_route")
                    }
                }
            )
        }
        composable(Screen.Search.route) { SearchPage() }

        // --- TAMBAHKAN ROUTE TUJUAN ANDA DI SINI ---
        composable("quran_route") {
            // Composable untuk halaman Quran Anda
            // Contoh: QuranScreen(navController = navController)
        }
        composable("kitab_route") {
            // Composable untuk halaman Adzan
        }
        composable("podcast_route") {
            // Composable untuk halaman Qibla
        }
        composable("settings_route") {
            // Composable untuk halaman Pengaturan
        }
    }
}