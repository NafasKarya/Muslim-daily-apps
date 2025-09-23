// File: com/nafaskarya/muslimdaily/components/widgets/dashboard/DashboardHeader.kt

package com.nafaskarya.muslimdaily.components.widgets.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.nafaskarya.muslimdaily.layouts.text.Strings
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons
import com.nafaskarya.muslimdaily.layouts.theme.Dimens
import com.nafaskarya.muslimdaily.ui.utils.getCurrentTimeGreeting // <--- Fix import!

/**
 * Header yang tampil saat di-scroll ke bawah.
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
fun DashboardHeader() {
    // 1. Pakai getCurrentTimeGreeting, bukan getCurrentTimeOfDay
    val timeGreeting = remember { getCurrentTimeGreeting() }
    val timeOfDay = timeGreeting.timeOfDay

    // 2. Gradient dari backgroundColor ke putih
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
                text = timeGreeting.greetingText, // <--- Ambil dari holder
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
