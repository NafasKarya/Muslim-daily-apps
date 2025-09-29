package com.nafaskarya.muslimdaily.components.widgets.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons
import com.nafaskarya.muslimdaily.ui.utils.Screen

/**
 * Data class yang telah disesuaikan untuk item navigasi.
 * Sekarang menggunakan @DrawableRes Int untuk ikon, sesuai dengan AppIcons.kt.
 */
data class NavItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route: String
) {
    /**
     * Fungsi bantuan untuk mendapatkan painter yang sesuai
     * berdasarkan apakah item ini sedang dipilih (selected).
     */
    @Composable
    fun getIconPainter(isSelected: Boolean): Painter {
        return painterResource(id = if (isSelected) selectedIcon else unselectedIcon)
    }
}

/**
 * Daftar item navigasi yang terpusat dan sudah disesuaikan.
 * Referensi ikon sekarang benar sesuai dengan AppIcons.kt.
 */
val guestDashboardNavItems = listOf(
    NavItem(
        title = "Home",
        selectedIcon = AppIcons.Home.active,
        unselectedIcon = AppIcons.Home.inactive,
        route = Screen.Dashboard.route
    ),
    NavItem(
        title = "Search",
        selectedIcon = AppIcons.Search.active,
        unselectedIcon = AppIcons.Search.inactive,
        route = Screen.Search.route
    ),
    NavItem(
        title = "Masjidku",
        selectedIcon = AppIcons.Mosque.active,
        unselectedIcon = AppIcons.Mosque.inactive,
        route = "masjid_route" // TODO: Ganti dengan route yang sesuai
    ),
    NavItem(
        title = "Favorite",
        selectedIcon = AppIcons.Favorite.active,
        unselectedIcon = AppIcons.Favorite.inactive,
        route = "favorite_route" // TODO: Ganti dengan route yang sesuai
    )
    // Jika Anda ingin menambahkan Profile, bisa seperti ini:
    /*
    NavItem(
        title = "Profile",
        selectedIcon = AppIcons.Profile.active,
        unselectedIcon = AppIcons.Profile.inactive,
        route = "profile_route" // TODO: Ganti dengan route yang sesuai
    )
    */
)