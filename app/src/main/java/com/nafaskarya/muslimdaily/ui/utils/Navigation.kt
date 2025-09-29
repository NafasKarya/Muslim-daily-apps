package com.nafaskarya.muslimdaily.ui.utils

// Gunakan sealed class agar rute lebih aman dan terorganisir
sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard_screen")
    object Search : Screen("search_screen")
    // Tambahkan rute lain di sini jika perlu, mis: Qibla, Tasbih
}