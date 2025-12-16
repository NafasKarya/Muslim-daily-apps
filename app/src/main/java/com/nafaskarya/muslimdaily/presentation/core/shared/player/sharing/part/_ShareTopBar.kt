package com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.constant.ShareScreenBackground
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareTopBar(
    dimen: WindowDimensions,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Share Lyrics",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen.getResponsiveTextSize(0.045f, max = 20f).value.sp
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextWhite
                )
            }
        },
        actions = {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = TextWhite
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ShareScreenBackground   // ✅ pakai constant baru
        )
    )
}
