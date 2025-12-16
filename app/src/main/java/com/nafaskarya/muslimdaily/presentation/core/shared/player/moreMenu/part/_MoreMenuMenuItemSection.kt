package com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun MenuRowItem(
    dimen: WindowDimensions,
    icon: ImageVector,
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO */ }
            .padding(
                horizontal = dimen.width * 0.05f,
                vertical = dimen.getResponsiveHeight(0.018f)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextWhite,
            modifier = Modifier.size(26.dp)
        )
        Spacer(modifier = Modifier.width(dimen.width * 0.05f))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TextWhite,
                fontSize = dimen.getResponsiveTextSize(0.04f, min = 14f),
                fontWeight = FontWeight.Medium
            )
        )
    }
}
