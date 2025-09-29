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
// --- PERUBAHAN 1: Tambahkan anotasi @RequiresApi ---
// Karena composable ini memanggil DashboardContent yang butuh API 26.
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent(
    navController: NavHostController,
    isLoading: Boolean,
    // --- PERUBAHAN 2: Tambahkan parameter onRefresh ---
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
                // --- PERUBAHAN 3: Kirimkan onRefresh ke DashboardContent ---
                onRefresh = onRefresh,
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(Screen.Search.route) { SearchPage() }
        // bisa tambahkan route lain: Qibla, Tasbih
    }
}