package com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun ColumnScope.ShareLyricsCardSection(   // 👈 extension di ColumnScope
    dimen: WindowDimensions,
    selectedColor: Color
) {
    val isLightCard = selectedColor == Color.White
    val primaryTextColor = if (isLightCard) Color.Black else TextWhite
    val secondaryTextColor = if (isLightCard) Color.Gray else TextWhite.copy(alpha = 0.7f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f) // 👈 sekarang legal, lagi di ColumnScope
            .padding(
                horizontal = dimen.width * 0.05f,
                vertical = dimen.getResponsiveHeight(0.02f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(dimen.width * 0.05f),
            colors = CardDefaults.cardColors(containerColor = selectedColor),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimen.width * 0.06f)
            ) {
                // HEADER
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        modifier = Modifier
                            .size(dimen.width * 0.16f)
                            .padding(1.dp),
                        shape = RoundedCornerShape(6.dp),
                        shadowElevation = 6.dp,
                        color = Color.Black
                    ) {
                        AsyncImage(
                            model = "https://upload.wikimedia.org/wikipedia/en/0/0e/Giyu_Tomioka.jpg",
                            contentDescription = "Album Art",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "Cinta Yang Tulus (feat. Gigi)",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = primaryTextColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = dimen.getResponsiveTextSize(
                                    0.038f,
                                    min = 14f
                                )
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Gito Rollies",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = secondaryTextColor,
                                fontSize = dimen.getResponsiveTextSize(
                                    0.032f,
                                    min = 11f
                                ),
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.03f)))

                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = secondaryTextColor,
                    modifier = Modifier.size(dimen.width * 0.06f)
                )

                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.02f)))

                val lyricStyle = MaterialTheme.typography.headlineSmall.copy(
                    color = primaryTextColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen.getResponsiveTextSize(
                        0.055f,
                        min = 18f,
                        max = 24f
                    ),
                    lineHeight = dimen.getResponsiveTextSize(0.075f).value.sp
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        dimen.getResponsiveHeight(0.015f)
                    )
                ) {
                    Text("Cinta yang tulus di dalam hatiku", style = lyricStyle)
                    Text("T'lah bersemi karena-Mu", style = lyricStyle)
                    Text("Hati yang suram kini tiada lagi", style = lyricStyle)
                    Text("T'lah bersinar karena-Mu", style = lyricStyle)
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.PlayCircleFilled,
                        contentDescription = "Logo",
                        tint = secondaryTextColor,
                        modifier = Modifier.size(dimen.width * 0.06f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Muslim Daily",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = secondaryTextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = dimen.getResponsiveTextSize(
                                0.035f,
                                min = 12f
                            )
                        )
                    )
                }
            }
        }
    }
}
