package com.nafaskarya.muslimdaily.presentation.core.shared.player.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerTextSecondary
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun PlayerHeaderSection(
    modifier: Modifier = Modifier,
    onCollapse: () -> Unit,
    onMoreClick: () -> Unit    // 👈 callback baru
) {
    val dimen = rememberWindowDimensions()

    Row(
        modifier = modifier
            .statusBarsPadding()
            .padding(top = dimen.getResponsiveHeight(0.01f)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onCollapse) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Collapse",
                tint = TextWhite,
                modifier = Modifier.size(dimen.width * 0.085f)
            )
        }

        Text(
            text = "RECOMMENDED FOR YOU",
            style = MaterialTheme.typography.labelSmall.copy(
                color = PlayerTextSecondary,
                fontWeight = FontWeight.Bold,
                fontSize = dimen.getResponsiveTextSize(0.025f, min = 10f, max = 14f)
            )
        )

        IconButton(onClick = onMoreClick) {   // 👈 panggil callback
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = TextWhite,
                modifier = Modifier.size(dimen.width * 0.07f)
            )
        }
    }
}
