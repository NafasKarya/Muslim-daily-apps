package com.nafaskarya.muslimdaily.presentation.core.shared.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

// --- DATA MODEL (bisa dipakai di mana-mana) ---
data class DiscoverSong(
    val title: String,
    val artist: String,
    val plays: String,
    val contextInfo: String,
    val imageModel: Any        // 👈 diganti dari imageUrl: String ke Any
)

// --- SECTION SHARED (header + slider) ---
@Composable
fun SharedDiscoverCardSection(
    title: String,
    songs: List<DiscoverSong>,
    onPlayAllClick: () -> Unit,
    onCardClick: (DiscoverSong) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimen = rememberWindowDimensions()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimen.getResponsiveHeight(0.02f))
    ) {
        // HEADER SECTION
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimen.width * 0.05f, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen.getResponsiveTextSize(0.055f, min = 18f, max = 24f)
                )
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(50))
                    .clickable { onPlayAllClick() }
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Play all",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = TextWhite,
                        fontWeight = FontWeight.Medium,
                        fontSize = dimen.getResponsiveTextSize(0.032f, min = 12f)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // SLIDER ROW
        LazyRow(
            contentPadding = PaddingValues(horizontal = dimen.width * 0.05f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(songs) { song ->
                SharedDiscoverCardItem(
                    dimen = dimen,
                    song = song,
                    onClick = { onCardClick(song) }
                )
            }
        }
    }
}

// --- SINGLE CARD SHARED ---
@Composable
fun SharedDiscoverCardItem(
    dimen: WindowDimensions,
    song: DiscoverSong,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val cardWidth = dimen.width * 0.85f
    val cardHeight = cardWidth

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        modifier = modifier
            .width(cardWidth)
            .height(cardHeight)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image (bisa URL atau drawable res ID)
            AsyncImage(
                model = song.imageModel,          // 👈 pakai imageModel, type Any
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay biar teks kebaca
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.8f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
            )

            // Content Text
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // TOP: title + artist
                Column {
                    Text(
                        text = song.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = dimen.getResponsiveTextSize(
                                0.05f,
                                min = 18f,
                                max = 22f
                            )
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${song.artist} • ${song.plays}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextWhite.copy(alpha = 0.7f),
                            fontSize = dimen.getResponsiveTextSize(0.035f, min = 12f)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // BOTTOM: context info
                Text(
                    text = song.contextInfo,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextWhite.copy(alpha = 0.9f),
                        fontStyle = FontStyle.Italic,
                        fontSize = dimen.getResponsiveTextSize(0.032f, min = 11f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
