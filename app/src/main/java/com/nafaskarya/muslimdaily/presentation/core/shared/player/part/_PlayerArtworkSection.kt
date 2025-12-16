package com.nafaskarya.muslimdaily.presentation.core.shared.player.part

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite

@Composable
fun PlayerArtworkSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(TextWhite.copy(alpha = 0.1f))
    ) {
        AsyncImage(
            model = "https://upload.wikimedia.org/wikipedia/en/0/0e/Giyu_Tomioka.jpg",
            contentDescription = "Album Art",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
    }
}
