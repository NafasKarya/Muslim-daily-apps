package com.nafaskarya.muslimdaily.components.widgets.guestUser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.nafaskarya.muslimdaily.components.widgets.CustomBottomBar
import com.nafaskarya.muslimdaily.components.widgets.data.NavItem

/**
 * Layout untuk layar COMPACT (HP), menggunakan Bottom Navigation Bar.
 */
@Composable
fun CompactScreenLayout(
    navController: NavHostController,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    navColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            AppContent(navController)
        }
        CustomBottomBar(
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = onItemSelected,
            navColor = navColor
        )
    }
}

/**
 * Layout untuk layar MEDIUM & EXPANDED (Tablet), menggunakan Navigation Rail di samping.
 */
@Composable
fun ExpandedScreenLayout(
    navController: NavHostController,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    navColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavigationRail(
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = selectedItemIndex == index,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Icon(
                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = navColor,
                        selectedTextColor = navColor,
                        indicatorColor = navColor.copy(alpha = 0.1f)
                    )
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            AppContent(navController)
        }
    }
}