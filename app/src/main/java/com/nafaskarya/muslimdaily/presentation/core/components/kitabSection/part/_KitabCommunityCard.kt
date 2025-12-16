package com.nafaskarya.muslimdaily.presentation.core.components.kitabSection.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.presentation.core.components.kitabSection.CommunitySong
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.shared.card.CircleIconButton
import com.nafaskarya.muslimdaily.presentation.core.shared.card.GradientRoundedCard
import com.nafaskarya.muslimdaily.presentation.core.shared.card.PrimaryPillButton
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

// Warna Background Kartu khusus section Kitab
private val CardGradientColors = listOf(
    Color(0xFF3E2B2B), // Coklat Kemerahan Atas
    Color(0xFF1F1515)  // Gelap Bawah
)

@Composable
fun KitabCommunityCard(dimen: WindowDimensions) {
    val cardWidth = dimen.width * 0.88f

    GradientRoundedCard(
        modifier = Modifier
            .width(cardWidth)
            .padding(vertical = 8.dp),
        cornerRadius = 16.dp,
        gradientColors = CardGradientColors
    ) {
        // 1. HEADER (Collage Image + Title)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CollageCoverImage(size = dimen.width * 0.28f)

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Kisah Hewan Aneh di Kitab Klasik",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimen.getResponsiveTextSize(0.05f, min = 18f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Cerita-cerita legend, bahasanya Gen Z",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextWhite.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium,
                        fontSize = dimen.getResponsiveTextSize(0.035f, min = 12f)
                    )
                )
                Text(
                    text = "87 kisah pilihan dari kitab klasik",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextWhite.copy(alpha = 0.5f),
                        fontSize = dimen.getResponsiveTextSize(0.03f, min = 11f)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 2. LIST KISAH (masih pakai CommunitySong, tapi image-nya ignore & pakai asset lokal)
        val songs = listOf(
            CommunitySong(
                title = "Unta yang Nangis di Tengah Padang",
                artist = "Tentang rasa bersalah & taubat • versi ringkas buat Gen Z",
                imageUrl = "" // diabaikan, pakai drawable
            ),
            CommunitySong(
                title = "Burung Pembawa Pesan Rahasia",
                artist = "Ngomongin amanah, chat rahasia, dan jaga titipan",
                imageUrl = ""
            ),
            CommunitySong(
                title = "Serigala yang Jadi Saksi",
                artist = "Kisah persidangan ala zaman dulu, tapi tetap relate",
                imageUrl = ""
            )
        )

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            songs.forEach { song ->
                CommunitySongItem(dimen, song)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. ACTION BUTTONS
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryPillButton(
                text = "Baca Sekarang",
                dimen = dimen,
                onClick = { /* TODO: Read Action */ }
            )

            CircleIconButton(
                icon = Icons.Outlined.ThumbUp,
                contentDescription = "Like",
                onClick = { /* TODO: Like */ }
            )
        }
    }
}

@Composable
fun CommunitySongItem(dimen: WindowDimensions, song: CommunitySong) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Pakai asset lokal, bukan URL
        Image(
            painter = painterResource(id = R.drawable.img_onboarding),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimen.width * 0.12f)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimen.getResponsiveTextSize(0.038f, min = 14f)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.artist,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextWhite.copy(alpha = 0.7f),
                    fontSize = dimen.getResponsiveTextSize(0.032f, min = 12f)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            tint = TextWhite.copy(alpha = 0.7f),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun CollageCoverImage(size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Kolase simple, tapi semua pakai img_onboarding biar konsisten offline
        Column {
            Row(Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
            Row(Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
        }
    }
}
