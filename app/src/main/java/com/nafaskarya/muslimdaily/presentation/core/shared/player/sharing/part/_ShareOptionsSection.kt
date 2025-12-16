package com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.constant.ShareSurfaceColor
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.ShareOption
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

// ✅ OPTIMASI 1: Buat Brush statis (Constant) agar tidak dibuat ulang di dalam loop
private val InstagramGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFFF58529),
        Color(0xFFDD2A7B),
        Color(0xFF8134AF),
        Color(0xFF515BD4)
    )
)

// ✅ OPTIMASI 2: Pindahkan List opsi ke luar Composable (Static Data)
private val StaticShareOptions = listOf(
    ShareOption("Copy link", Icons.Default.Link, ShareSurfaceColor),
    ShareOption("Stories", Icons.Default.CameraAlt, Color.Transparent, isGradient = true),
    ShareOption("WhatsApp", Icons.Default.Message, Color(0xFF25D366)),
    ShareOption("Notes", Icons.Default.Edit, Color(0xFFE91E63)),
    ShareOption("More", Icons.Default.Share, ShareSurfaceColor)
)

@Composable
fun ShareOptionsSection(
    dimen: WindowDimensions
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimen.getResponsiveHeight(0.04f))
    ) {
        // Internal Share Section
        Column(
            modifier = Modifier.padding(horizontal = dimen.width * 0.05f)
        ) {
            Text(
                text = "Share with Muslim Daily Messages",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(ShareSurfaceColor, CircleShape)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Invite",
                        tint = TextWhite,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Invite\nfriends",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextWhite.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        lineHeight = 14.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = TextWhite.copy(alpha = 0.1f),
            modifier = Modifier.padding(horizontal = dimen.width * 0.05f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // External Share Section
        LazyRow(
            contentPadding = PaddingValues(horizontal = dimen.width * 0.05f),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // ✅ OPTIMASI 3: Tambahkan Key & Gunakan list statis
            items(
                items = StaticShareOptions,
                key = { it.label } // Key unik dari label
            ) { option ->
                ShareOptionItem(option)
            }
        }
    }
}

// ✅ OPTIMASI 4: Pecah item menjadi Composable kecil agar lebih ringan (Skippability)
@Composable
private fun ShareOptionItem(option: ShareOption) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    brush = if (option.isGradient) InstagramGradient else null,
                    color = if (option.isGradient) Color.Transparent else option.color,
                    shape = CircleShape
                )
                .clickable { /* TODO handle share */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.label,
                tint = TextWhite,
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = option.label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextWhite.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                fontSize = 11.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// Extension helper kecil untuk modifier background agar lebih rapi
private fun Modifier.background(
    brush: Brush?,
    color: Color,
    shape: androidx.compose.ui.graphics.Shape
): Modifier = if (brush != null) {
    this.background(brush, shape)
} else {
    this.background(color, shape)
}