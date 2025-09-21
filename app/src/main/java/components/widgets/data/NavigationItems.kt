package com.nafaskarya.muslimdaily.components.widgets.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons
import com.nafaskarya.muslimdaily.ui.utils.Screen

/**
 * Data class yang lebih baik untuk item navigasi,
 * menyertakan route tujuan untuk navigasi yang lebih aman.
 */
data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

/**
 * Daftar item navigasi yang terpusat.
 * Jika Anda ingin menambah atau mengubah item, cukup edit di sini.
 */
val guestDashboardNavItems = listOf(
    NavItem(
        title = "Home",
        selectedIcon = AppIcons.HomeFilled,
        unselectedIcon = AppIcons.HomeOutlined,
        route = Screen.Dashboard.route
    ),
    NavItem(
        title = "Search",
        selectedIcon = AppIcons.SearchFilled, // Ganti dengan ikon yang sesuai
        unselectedIcon = AppIcons.SearchOutlined, // Ganti dengan ikon yang sesuai
        route = Screen.Search.route
    ),
    NavItem(
        title = "Masjidku",
        selectedIcon = AppIcons.MosqueFilled, // Ganti dengan ikon yang sesuai
        unselectedIcon = AppIcons.MosqueOutlined, // Ganti dengan ikon yang sesuai
        route = "masjid_route" // Ganti dengan route yang sesuai
    ),
    NavItem(
        title = "Favorite",
        selectedIcon = AppIcons.FavoriteFilled, // Ganti dengan ikon yang sesuai
        unselectedIcon = AppIcons.FavoriteOutlined, // Ganti dengan ikon yang sesuai
        route = "favorite_route" // Ganti dengan route yang sesuai
    )
)