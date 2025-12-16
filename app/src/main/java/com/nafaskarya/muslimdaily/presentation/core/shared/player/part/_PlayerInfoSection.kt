package com.nafaskarya.muslimdaily.presentation.core.shared.player.part

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerAccentGreen
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerTextSecondary
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun PlayerInfoSection(
    modifier: Modifier = Modifier
) {
    val dimen = rememberWindowDimensions()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Giyu's Demon Slayer Mark",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen.getResponsiveTextSize(
                        0.045f,
                        min = 16f,
                        max = 22f
                    )
                ),
                maxLines = 1,
                // overflow = TextOverflow.Ellipsis, 👈 HAPUS INI (diganti marquee)

                // 👇 TAMBAHKAN INI UNTUK EFEK MARQUEE
                modifier = Modifier.basicMarquee(
                    iterations = Int.MAX_VALUE, // Loop selamanya
                    velocity = 30.dp // Kecepatan scroll (sesuaikan jika perlu)
                )
            )

            Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.02f)))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(dimen.width * 0.04f)
                        .background(PlayerAccentGreen)
                        .padding(dimen.width * 0.005f)
                ) {
                    Text(
                        "E",
                        fontSize = dimen.getResponsiveTextSize(0.022f, min = 8f).value.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(dimen.width * 0.015f))
                Text(
                    text = "DIEGO MITRE",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PlayerTextSecondary,
                        fontSize = dimen.getResponsiveTextSize(0.038f, min = 12f)
                    )
                )
            }
        }

        Row {
            val actionIconSize = dimen.width * 0.075f
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "Add",
                    tint = TextWhite,
                    modifier = Modifier.size(actionIconSize)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove",
                    tint = TextWhite,
                    modifier = Modifier.size(actionIconSize)
                )
            }
        }
    }
}