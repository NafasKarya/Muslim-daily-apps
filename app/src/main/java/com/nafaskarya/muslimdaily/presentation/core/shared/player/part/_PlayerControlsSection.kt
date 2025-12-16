package com.nafaskarya.muslimdaily.presentation.core.shared.player.part

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerAccentGreen
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun PlayerControlsSection(
    // 👇 TAMBAHKAN PARAMETER INI AGAR TIDAK ERROR
    onTimerClick: () -> Unit
) {
    val dimen = rememberWindowDimensions()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val smallControlSize = dimen.width * 0.075f
        val skipControlSize = dimen.width * 0.12f

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Shuffle,
                contentDescription = "Shuffle",
                tint = PlayerAccentGreen,
                modifier = Modifier.size(smallControlSize)
            )
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = "Previous",
                tint = TextWhite,
                modifier = Modifier.size(skipControlSize)
            )
        }

        Surface(
            modifier = Modifier.size(dimen.width * 0.18f),
            shape = CircleShape,
            color = TextWhite
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.Black,
                    modifier = Modifier.size(dimen.width * 0.1f)
                )
            }
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = "Next",
                tint = TextWhite,
                modifier = Modifier.size(skipControlSize)
            )
        }

        // 👇 PASANG ACTION ONCLICK DISINI
        IconButton(onClick = onTimerClick) {
            Icon(
                imageVector = Icons.Default.Alarm,
                contentDescription = "Timer",
                tint = TextWhite,
                modifier = Modifier.size(smallControlSize)
            )
        }
    }
}