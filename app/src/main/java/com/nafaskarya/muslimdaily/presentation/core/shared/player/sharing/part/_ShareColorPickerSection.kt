package com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.ShareCardColors
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun ShareColorPickerSection(
    dimen: WindowDimensions,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = dimen.width * 0.05f),
        modifier = Modifier.padding(bottom = dimen.getResponsiveHeight(0.015f))
    ) {
        items(ShareCardColors) { color ->
            val isSelected = color == selectedColor
            Box(
                modifier = Modifier
                    .size(dimen.width * 0.11f)
                    .background(color, CircleShape)
                    .border(
                        width = if (isSelected) 2.5.dp else 0.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .padding(if (isSelected) 2.5.dp else 0.dp)
                    .clickable { onColorSelected(color) }
            )
        }
    }
}
