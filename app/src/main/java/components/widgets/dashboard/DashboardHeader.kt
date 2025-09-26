package com.nafaskarya.muslimdaily.components.widgets.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons
import com.nafaskarya.muslimdaily.layouts.theme.Dimens
import com.nafaskarya.muslimdaily.ui.utils.getCurrentTimeGreeting
import com.valentinilk.shimmer.shimmer

/**
 * Header yang tampil saat di-scroll ke bawah.
 * (Tidak ada perubahan)
 */
@Composable
fun TopAppBarWhenScrolled() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = Dimens.PaddingExtraSmall
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .height(Dimens.AppBarHeight)
                .padding(horizontal = Dimens.PaddingNormal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Muslim Daily",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Header utama yang tampil di bagian atas dashboard.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardHeader(
    // --- PERUBAHAN 1: Terima parameter isLoading ---
    isLoading: Boolean
) {
    // --- PERUBAHAN 2: Tampilkan Shimmer jika isLoading true, jika tidak tampilkan konten asli ---
    if (isLoading) {
        ShimmerDashboardHeader()
    } else {
        val timeGreeting = remember { getCurrentTimeGreeting() }
        val timeOfDay = timeGreeting.timeOfDay
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(timeOfDay.backgroundColor, Color.White)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = Dimens.DashboardHeaderHeight)
                .background(brush = gradientBrush)
                .padding(horizontal = Dimens.PaddingLarge, vertical = Dimens.PaddingXLarge)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopEnd)
                    .padding(top = Dimens.PaddingNormal),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Notification */ }) {
                    Icon(
                        painter = painterResource(id = AppIcons.Notification),
                        contentDescription = "Notifications",
                        tint = timeOfDay.textColor,
                        modifier = Modifier.size(Dimens.IconMedium)
                    )
                }
            }
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = timeGreeting.greetingText,
                    fontSize = Dimens.TextLarge,
                    fontWeight = FontWeight.Normal,
                    color = timeOfDay.textColor.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(Dimens.PaddingExtraSmall))
                Text(
                    text = Strings.UserName,
                    fontSize = Dimens.TextHeading,
                    fontWeight = FontWeight.Bold,
                    color = timeOfDay.textColor
                )
            }
        }
    }
}

/**
 * --- PERUBAHAN 3: Komponen baru untuk menampilkan placeholder shimmer ---
 */
@Composable
private fun ShimmerDashboardHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = Dimens.DashboardHeaderHeight)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Gray.copy(alpha = 0.2f), Color.White)
                )
            )
            .padding(horizontal = Dimens.PaddingLarge, vertical = Dimens.PaddingXLarge)
            .shimmer()
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            // Placeholder untuk sapaan
            Box(
                modifier = Modifier
                    .height(Dimens.TextLarge.value.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingExtraSmall))
            // Placeholder untuk nama pengguna
            Box(
                modifier = Modifier
                    .height(Dimens.TextHeading.value.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }
    }
}