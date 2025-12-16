package com.nafaskarya.muslimdaily.presentation.core.shared.player.part

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerTextSecondary
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row

@Composable
fun PlayerSeekBarSection() {
    val dimen = rememberWindowDimensions()
    var sliderPosition by remember { mutableFloatStateOf(0.3f) }

    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = TextWhite,
                activeTrackColor = TextWhite,
                inactiveTrackColor = TextWhite.copy(alpha = 0.3f)
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        val timeSize = dimen.getResponsiveTextSize(0.03f, min = 10f)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "0:00",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PlayerTextSecondary,
                    fontSize = timeSize
                )
            )
            Text(
                "2:18",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PlayerTextSecondary,
                    fontSize = timeSize
                )
            )
        }
    }
}

