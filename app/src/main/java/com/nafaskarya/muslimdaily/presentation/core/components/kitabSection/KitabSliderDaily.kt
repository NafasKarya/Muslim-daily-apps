package com.nafaskarya.muslimdaily.presentation.core.components.kitabSection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.components.kitabSection.part.KitabCommunityCard
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

// Dummy Data
data class CommunitySong(
    val title: String,
    val artist: String,
    val imageUrl: String
)

@Composable
fun KitabSliderDailySection() {
    val dimen = rememberWindowDimensions()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimen.getResponsiveHeight(0.02f))
    ) {
        // --- SECTION TITLE ---
        Text(
            text = "From the community",
            style = MaterialTheme.typography.titleLarge.copy(
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = dimen.getResponsiveTextSize(0.055f, min = 18f, max = 24f)
            ),
            modifier = Modifier.padding(
                horizontal = dimen.width * 0.05f,
                vertical = 12.dp
            )
        )

        // 👇 Spacer biar kartu gak nempel ke judul
        Spacer(
            modifier = Modifier.height(
                dimen.getResponsiveHeight(0.015f) // sekitar 8–12dp tergantung tinggi layar
            )
        )

        // --- SLIDER ROW ---
        LazyRow(
            contentPadding = PaddingValues(horizontal = dimen.width * 0.05f),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            items(3) {
                KitabCommunityCard(dimen)
            }
        }
    }
}
