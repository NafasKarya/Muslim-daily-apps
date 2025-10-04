package com.nafaskarya.muslimdaily.ui.kitab

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nafaskarya.muslimdaily.components.shared.kitab.KitabScreen

// Definisikan rute sebagai konstanta agar tidak ada typo
object AppRoutes {
    const val KITAB_MENU = "kitab_menu"
    const val KITAB_DETAIL = "kitab_detail"
}

@Composable
fun KitabAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.KITAB_MENU) {
        // Rute untuk layar menu utama
        composable(AppRoutes.KITAB_MENU) {
            KitabMenuScreen(
                // Kirim lambda untuk menangani navigasi saat item diklik
                onNavigateToDetail = {
                    navController.navigate(AppRoutes.KITAB_DETAIL)
                }
            )
        }

        // Rute untuk layar detail kitab
        composable(AppRoutes.KITAB_DETAIL) {
            // Panggil layar detail Anda di sini
            KitabScreen()
        }
    }
}