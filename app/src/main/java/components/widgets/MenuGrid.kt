package com.nafaskarya.muslimdaily.components.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppImages

// Data class untuk setiap item menu
data class MenuItem(
    val title: String,
    @DrawableRes val iconRes: Int
)

// Komponen utama yang menampilkan seluruh grid menu
@Composable
fun MenuGrid() {
    val menuItems = listOf(
        MenuItem("Quran", AppImages.AlQuran),
        MenuItem("Adzan", AppImages.Reminder),
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
                    onClick = { /* TODO: Tambahkan aksi navigasi di sini */ },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Komponen untuk satu kartu item menu
@Composable
fun MenuItemCard(menuItem: MenuItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Card(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(16.dp),
            // Elevation untuk memberikan efek bayangan (shadow)
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            // Warna latar belakang kartu adalah putih
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = menuItem.iconRes),
                    contentDescription = menuItem.title,
                    modifier = Modifier.size(28.dp)
                    // Tidak ada colorFilter agar warna asli ikon tampil
                )
            }
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

// Preview untuk melihat hasil di Android Studio
@Preview(showBackground = true)
@Composable
private fun MenuGridPreview() {
    MenuGrid()
}