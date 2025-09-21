package com.nafaskarya.muslimdaily.components.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppImages

// Data class menu
data class MenuItem(
    val title: String,
    @DrawableRes val iconRes: Int
)

@Composable
fun MenuGrid() {
    val menuItems = listOf(
        MenuItem("Quran", AppImages.AlQuran),
        MenuItem("Adzan", AppImages.Reminder), // contoh icon
        MenuItem("Qibla", AppImages.Qibla),
        MenuItem("Donation", AppImages.DzikirDaily),
        MenuItem("All", AppImages.DuaDaily)
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "All Features",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            menuItems.forEach { item ->
                MenuItemCard(
                    menuItem = item,
                    onClick = { /* TODO: aksi */ },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = Color(0xFF1ABC9C), // hijau teal
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = menuItem.iconRes),
                contentDescription = menuItem.title,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = menuItem.title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
