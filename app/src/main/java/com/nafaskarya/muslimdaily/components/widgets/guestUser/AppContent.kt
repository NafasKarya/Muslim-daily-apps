package com.nafaskarya.muslimdaily.components.widgets.guestUser

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nafaskarya.muslimdaily.components.shared.SearchPage
import com.nafaskarya.muslimdaily.ui.utils.Screen

/**
 * Konten aplikasi (NavHost) yang digunakan bersama oleh semua layout.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent(
    navController: NavHostController,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onShowSnackbar: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardContent(
                isLoading = isLoading,
                onRefresh = onRefresh,
                onShowSnackbar = onShowSnackbar,
                // --- PERBAIKAN: Tambahkan parameter onMenuItemClick ---
                onMenuItemClick = { menuItem ->
                    // Di sinilah logika navigasi utama Anda
                    when (menuItem.title) {
                        "Quran" -> {
                            navController.navigate("quran_route") // Ganti dengan route Quran Anda
                        }
                        "Adzan" -> {
                            // navController.navigate("adzan_route")
                        }
                        "Qibla" -> {
                            // navController.navigate("qibla_route")
                        }
                        // Tambahkan case lain jika diperlukan
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
        // bisa tambahkan route lain: qibla_route, dll.
    }
}