package com.nafaskarya.muslimdaily.presentation.core.components.cardSection.CardSlider

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.shared.card.SharedSmallSliderCard
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

data class HitItem(
    val id: Int,
    val description: String
)

@Composable
fun MoreLikeThis(
    // 👇 1. Tambahkan parameter ini untuk menangkap event klik
    onItemClick: (Int) -> Unit
) {
    val dimen = rememberWindowDimensions()

    val hitList = listOf(
        HitItem(1, "Ust. Adi Hidayat • Tafsir & fiqih yang relate buat Gen Z"),
        HitItem(2, "Ust. Hanan Attaki • hijrah, galau, dan overthinking versi islami"),
        HitItem(3, "Ust. Abdul Somad • aqidah, adab, dan jawab-jawab ringan"),
        HitItem(4, "Nafaskarya • Muslim Daily Originals untuk teman ibadah harianmu")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimen.getResponsiveHeight(0.02f))
    ) {
        Text(
            text = "Kajian islami buat Gen Z",
            style = MaterialTheme.typography.titleLarge.copy(
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = dimen.getResponsiveTextSize(0.055f, min = 18f, max = 24f)
            ),
            modifier = Modifier.padding(horizontal = dimen.width * 0.05f, vertical = 12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = dimen.width * 0.05f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(hitList) { item ->
                SharedSmallSliderCard(
                    dimen = dimen,
                    imageModel = R.drawable.img_onboarding,
                    description = item.description,
                    // 👇 2. Panggil callback saat kartu diklik
                    onClick = { onItemClick(item.id) }
                )
            }
        }
    }
}