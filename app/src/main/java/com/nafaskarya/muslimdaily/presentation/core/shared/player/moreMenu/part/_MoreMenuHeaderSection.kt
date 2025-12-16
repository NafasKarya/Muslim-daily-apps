package com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.part

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun MoreMenuHeader(
    dimen: WindowDimensions,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimen.width * 0.05f,
                vertical = dimen.getResponsiveHeight(0.02f)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Bitter Sweet Symphony",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen.getResponsiveTextSize(
                        0.045f,
                        min = 16f,
                        max = 20f
                    )
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "The Verve • 5:58",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextWhite.copy(alpha = 0.7f),
                    fontSize = dimen.getResponsiveTextSize(0.035f, min = 12f)
                )
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* TODO: Like action */ }) {
                Icon(
                    imageVector = Icons.Outlined.ThumbUp,
                    contentDescription = "Like",
                    tint = TextWhite,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = TextWhite,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
