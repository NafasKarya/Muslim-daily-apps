package com.nafaskarya.muslimdaily.presentation.core.shared.player.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerTextSecondary
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun PlayerFooterActionsSection(
    onShareClick: () -> Unit
) {
    val dimen = rememberWindowDimensions()
    val footerIconSize = dimen.width * 0.065f

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Cast,
                contentDescription = "Device",
                tint = PlayerTextSecondary,
                modifier = Modifier.size(footerIconSize)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onShareClick) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = PlayerTextSecondary,
                modifier = Modifier.size(footerIconSize)
            )
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.QueueMusic,
                contentDescription = "Queue",
                tint = PlayerTextSecondary,
                modifier = Modifier.size(footerIconSize)
            )
        }
    }
}
