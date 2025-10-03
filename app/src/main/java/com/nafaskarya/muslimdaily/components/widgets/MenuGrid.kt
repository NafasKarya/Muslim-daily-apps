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
import timber.log.Timber // Opsional, untuk logging di preview

// Data class untuk setiap item menu
data class MenuItem(
    val title: String,
    @DrawableRes val iconRes: Int
)

// Komponen utama yang menampilkan seluruh grid menu
@Composable
fun MenuGrid(
    // 1. TAMBAHKAN PARAMETER INI:
    // Parameter ini berfungsi sebagai "callback" atau pemicu
    // yang akan dijalankan ketika salah satu item menu di-klik.
    onMenuItemClick: (MenuItem) -> Unit
) {
    val menuItems = listOf(
        MenuItem("Quran", AppImages.AlQuran),
        MenuItem("Kitab Literal", AppImages.Reminder),
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
                    // 2. UBAH BAGIAN INI:
                    // Saat kartu di-klik, panggil callback `onMenuItemClick`
                    // dan kirimkan data item yang di-klik (`item`).
                    onClick = { onMenuItemClick(item) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Komponen untuk satu kartu item menu (Tidak ada perubahan di sini)
@Composable
fun MenuItemCard(menuItem: MenuItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Card(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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
    // 3. SESUAIKAN PREVIEW:
    // Kita berikan aksi sederhana untuk preview, misalnya mencetak ke log.
    MenuGrid(onMenuItemClick = { menuItem ->
        Timber.d("Item clicked: ${menuItem.title}")
    })
}