package com.nafaskarya.muslimdaily.presentation.core.components.kitabSection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.components.kitabSection.part.KitabCommunityCard
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

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

        Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.015f)))

        LazyRow(
            contentPadding = PaddingValues(horizontal = dimen.width * 0.05f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ✅ OPTIMASI 4: Tambahkan KEY dan count
            items(
                count = 3,
                key = { index -> index }
            ) {
                KitabCommunityCard(dimen)
            }
        }
    }
}