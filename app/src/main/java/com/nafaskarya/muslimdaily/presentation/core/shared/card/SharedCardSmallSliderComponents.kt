package com.nafaskarya.muslimdaily.presentation.core.shared.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

/**
 * Shared small card untuk slider (image square + text 2 baris).
 * imageModel bisa String (URL) atau Int (R.drawable.xxx).
 */
@Composable
fun SharedSmallSliderCard(
    dimen: WindowDimensions,
    imageModel: Any,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    // Lebar card ~38% layar biar muat 2–3 kartu di layar
    val cardWidth = dimen.width * 0.38f

    Column(
        modifier = modifier
            .width(cardWidth)
            .clickable { onClick() }
    ) {
        // Cover image persegi
        AsyncImage(
            model = imageModel,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(cardWidth) // square
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Deskripsi / list artis
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                fontSize = dimen.getResponsiveTextSize(0.032f, min = 11f).value.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = dimen.getResponsiveTextSize(0.04f).value.sp
        )
    }
}
