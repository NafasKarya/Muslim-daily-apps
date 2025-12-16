package com.nafaskarya.muslimdaily.presentation.core.shared.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

// ===== MODEL DATA =====
data class ShortSong(
    val title: String,
    val artist: String,
    val views: String,
    val imageUrl: String
)

// ===== SECTION SHARED COMPONENT =====
@Composable
fun SharedSliderCardSection(
    title: String,
    songs: List<ShortSong>,
    modifier: Modifier = Modifier,
    onPlayAllClick: () -> Unit = {},
    onSongClick: (ShortSong) -> Unit = {},
    onSongMoreClick: (ShortSong) -> Unit = {}
) {
    val dimen = rememberWindowDimensions()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimen.getResponsiveHeight(0.02f))
    ) {
        // --- HEADER SECTION ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimen.width * 0.05f,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen
                        .getResponsiveTextSize(0.06f, min = 20f, max = 26f)
                        .value.sp
                )
            )

            // "Play all" (outlined style)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .border(
                        1.dp,
                        Color.Gray.copy(alpha = 0.5f),
                        RoundedCornerShape(50)
                    )
                    .clickable { onPlayAllClick() }
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Play all",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = TextWhite,
                        fontWeight = FontWeight.Medium,
                        fontSize = dimen
                            .getResponsiveTextSize(0.032f, min = 12f)
                            .value.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- SONG LIST ---
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            songs.forEach { song ->
                SharedShortSongItem(
                    song = song,
                    dimen = dimen,
                    onClick = { onSongClick(song) },
                    onMoreClick = { onSongMoreClick(song) }
                )
            }
        }
    }
}

// ===== ITEM SHARED COMPONENT =====
@Composable
fun SharedShortSongItem(
    song: ShortSong,
    dimen: WindowDimensions,
    onClick: () -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    val dimenWidth = dimen.width

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = dimenWidth * 0.05f), // padding kiri kanan 5%
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. Album Art
        AsyncImage(
            model = song.imageUrl,
            contentDescription = song.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimenWidth * 0.13f) // ~50–60dp
                .clip(RoundedCornerShape(6.dp))
                .background(Color.DarkGray)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // 2. Text Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen
                        .getResponsiveTextSize(0.04f, min = 14f)
                        .value.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${song.artist} • ${song.views}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = dimen
                        .getResponsiveTextSize(0.032f, min = 12f)
                        .value.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // 3. More Icon
        IconButton(
            onClick = onMoreClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = TextWhite
            )
        }
    }
}
