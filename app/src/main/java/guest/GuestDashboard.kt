package com.nafaskarya.muslimdaily.guest


import NewestCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.components.widgets.*
import com.nafaskarya.muslimdaily.components.widgets.prayertime.PrayerTimeCard
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons
import com.nafaskarya.muslimdaily.layouts.theme.Dimens

@Composable
fun GuestDashboard() {
    val items = listOf(
        BottomNavItem("Home", AppIcons.HomeFilled, AppIcons.HomeOutlined),
        BottomNavItem("Quran", AppIcons.HomeFilled, AppIcons.HomeOutlined),
        BottomNavItem("Qibla", AppIcons.HomeFilled, AppIcons.HomeOutlined),
        BottomNavItem("Tasbih", AppIcons.HomeFilled, AppIcons.HomeOutlined),
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val navColor = Color(0xFF8B5A33)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Konten utama scrollable
        Box(modifier = Modifier.weight(1f)) {
            DashboardContent()
        }

        // ✅ panggil bottom bar dari component
        CustomBottomBar(
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = { selectedItemIndex = it },
            navColor = navColor
        )
    }
}

@Composable
fun DashboardContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DashboardHeader()
        LastReadCard()
        Spacer(modifier = Modifier.height(24.dp))
        Strings.PrayerTimeTitle
        Spacer(modifier = Modifier.height(12.dp))
        PrayerTimeCard()
        Spacer(modifier = Modifier.height(16.dp))
        FindMosqueButton(onClick = { /* ... */ })
        Spacer(modifier = Modifier.height(24.dp))
        MenuGrid()

        // ⬇️ Tambahan sesuai permintaan lo: UI baru di bawah MenuGrid
        Spacer(modifier = Modifier.height(24.dp))
        NgajiOnlineSection()

        Spacer(modifier = Modifier.height(24.dp))
        NewestCard()
    }
}

@Composable
private fun DashboardHeader() {
    val gradientStart = Color(0xFFE0FFE0)
    val gradientEnd = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(gradientStart, gradientEnd)
                )
            )
            .padding(horizontal = Dimens.PaddingLarge, vertical = Dimens.PaddingXLarge)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Notification */ }) {
                Icon(
                    imageVector = AppIcons.Notification,
                    contentDescription = "Notifications",
                    tint = Color.Black,
                    modifier = Modifier.size(Dimens.IconMedium)
                )
            }
            Spacer(modifier = Modifier.width(Dimens.PaddingMedium))
            IconButton(onClick = { /* Menu */ }) {
                Icon(
                    imageVector = AppIcons.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(Dimens.IconMedium)
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text(
                text = Strings.Greeting,
                fontSize = Dimens.TextXLarge,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
            Text(
                text = Strings.UserName,
                fontSize = Dimens.TextXXLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun GuestDashboardPreview() {
    GuestDashboard()
}
