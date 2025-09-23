// File: com/nafaskarya/muslimdaily/components/widgets/ResponsiveAppNavigation.kt

package com.nafaskarya.muslimdaily.components.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // <-- Import ditambahkan
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.components.widgets.data.NavItem

@Composable
fun ResponsiveAppNavigation(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowWidthSizeClass,
    items: List<NavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> {
            NavigationBar(
                modifier = modifier,
                // PERUBAHAN: Warna latar belakang diatur menjadi putih
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { onItemSelected(index) },
                        label = { Text(text = item.title, fontSize = 14.sp) },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    id = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon
                                ),
                                contentDescription = item.title,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    )
                }
            }
        }
        else -> { // Medium dan Expanded
            NavigationRail(
                modifier = modifier,
                // PERUBAHAN: Warna latar belakang diatur menjadi putih
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationRailItem(
                        selected = selectedItemIndex == index,
                        onClick = { onItemSelected(index) },
                        label = { Text(text = item.title, fontSize = 14.sp) },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    id = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon
                                ),
                                contentDescription = item.title,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}