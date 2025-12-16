package com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.part

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.PlaylistAdd as PlaylistAddFilled
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.MoreMenuButtonBackground
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun TopActionsRow(
    dimen: WindowDimensions
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimen.width * 0.02f,
                vertical = dimen.getResponsiveHeight(0.025f)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BigActionButton(
            dimen = dimen,
            icon = Icons.AutoMirrored.Filled.PlaylistAdd,
            label = "Play next"
        )
        BigActionButton(
            dimen = dimen,
            icon = Icons.AutoMirrored.Filled.PlaylistAdd, // alias dari filled.PlaylistAdd
            label = "Save to playlist"
        )
        BigActionButton(
            dimen = dimen,
            icon = Icons.Filled.Share,
            label = "Share"
        )
    }
}

@Composable
fun BigActionButton(
    dimen: WindowDimensions,
    icon: ImageVector,
    label: String
) {
    val boxSize = dimen.width * 0.28f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(boxSize)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f)
                .clip(RoundedCornerShape(12.dp))
                .background(MoreMenuButtonBackground)
                .clickable { /* TODO: onClick */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = TextWhite,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextWhite.copy(alpha = 0.9f),
                fontSize = dimen.getResponsiveTextSize(0.03f, min = 11f),
                fontWeight = FontWeight.Medium
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
