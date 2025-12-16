package com.nafaskarya.muslimdaily.presentation.core.shared.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerFooterBackground
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerIconColor
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerTextSecondary
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun PlayerFooter(
    modifier: Modifier = Modifier,
    title: String = "Judul Kajian Yang Sangat Panjang Sekali Sampai Harus Muter Muter",
    artist: String = "Ustadz Hanan Attaki",
    imageRes: Int = R.drawable.img_onboarding,
    onPlayPauseClick: () -> Unit = {},
    onDeviceClick: () -> Unit = {},
    onAddToPlaylistClick: () -> Unit = {},
    onItemClick: () -> Unit = {}
) {
    val dimen = rememberWindowDimensions()

    val titleSize = dimen.getResponsiveTextSize(0.04f, min = 14f, max = 18f)
    val artistSize = dimen.getResponsiveTextSize(0.032f, min = 12f, max = 16f)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(64.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(8.dp),
        color = PlayerFooterBackground,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Album Art",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = TextWhite,
                    fontSize = titleSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.basicMarquee(
                        iterations = Int.MAX_VALUE,
                        velocity = 30.dp,
                        initialDelayMillis = 2000
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.GraphicEq,
                        contentDescription = null,
                        tint = PlayerIconColor,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = artist,
                        color = PlayerTextSecondary,
                        fontSize = artistSize,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                IconButton(onClick = onDeviceClick) {
                    Icon(
                        imageVector = Icons.Default.Devices,
                        contentDescription = "Devices",
                        tint = PlayerIconColor.copy(alpha = 0.9f),
                        modifier = Modifier.size(22.dp)
                    )
                }

                IconButton(onClick = onAddToPlaylistClick) {
                    Icon(
                        imageVector = Icons.Default.AddCircleOutline,
                        contentDescription = "Add to Playlist",
                        tint = PlayerIconColor.copy(alpha = 0.9f),
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(onClick = onPlayPauseClick) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = PlayerIconColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun PlayerFooterPreview() {
    Box(modifier = Modifier.padding(20.dp)) {
        PlayerFooter(
            title = "Kajian Spesial: Menata Hati yang Sedang Gundah Gulana Bersama Ustadz Favorit",
            artist = "Ustadz Hanan Attaki"
        )
    }
}
