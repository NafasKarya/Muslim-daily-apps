package com.nafaskarya.muslimdaily.presentation.core.shared.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VerticalSplit
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

// --- Colors Constant ---
private val ItemSelected = Color(0xFFFFFFFF)
private val ItemUnselected = Color(0xFFB3B3B3)

// --- Data Model ---
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun BottomNav(
    currentRoute: String = "home",
    onNavigate: (String) -> Unit = {}
) {
    val dimen = rememberWindowDimensions()

    // Responsive Sizes
    val textSize = dimen.getResponsiveTextSize(0.028f, min = 11f, max = 13f)
    val iconSize = dimen.getResponsiveHeight(0.032f).coerceIn(24.dp, 28.dp)

    // Tinggi Navigasi
    val navHeight = dimen.getResponsiveHeight(0.09f).coerceIn(90.dp, 100.dp)

    // Navigation Items
    val items = remember {
        listOf(
            BottomNavItem("Home", Icons.Default.Home, "home"),
            BottomNavItem("Search", Icons.Default.Search, "search"),
            BottomNavItem("Your Library", Icons.Default.VerticalSplit, "library"),
            BottomNavItem("Premium", Icons.Default.WorkspacePremium, "premium"),
            BottomNavItem("Create", Icons.Default.Add, "create")
        )
    }

    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .height(navHeight)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        // Atas: Tetap transparan agar konten terlihat
                        Color.Black.copy(alpha = 0.0f),

                        // Tengah: Dipergelap (tadinya 0.2 jadi 0.4)
                        Color.Black.copy(alpha = 0.4f),

                        // Tengah ke Bawah: Makin gelap (tadinya 0.6 jadi 0.8)
                        Color.Black.copy(alpha = 0.85f),

                        // Bawah: Hitam Solid (Double agar area solidnya lebih tinggi)
                        Color.Black,
                        Color.Black
                    )
                )
            ),
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(iconSize)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = textSize,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ItemSelected,
                    selectedTextColor = ItemSelected,
                    unselectedIconColor = ItemUnselected,
                    unselectedTextColor = ItemUnselected,
                    indicatorColor = Color.Transparent
                ),
                alwaysShowLabel = true
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF333333)
@Composable
fun BottomNavPreview() {
    var currentRoute by remember { mutableStateOf("home") }
    BottomNav(
        currentRoute = currentRoute,
        onNavigate = { route -> currentRoute = route }
    )
}