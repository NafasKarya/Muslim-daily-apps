package com.nafaskarya.muslimdaily.components.shared.shimmer.quran

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuranListLoadingShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(0.6f)
                .shimmerEffect() // Memakai default shape
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier = Modifier
                    .height(80.dp)
                    .weight(1f)
                    .shimmerEffect() // Memakai default shape
            )
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(
                modifier = Modifier
                    .height(80.dp)
                    .weight(1f)
                    .shimmerEffect() // Memakai default shape
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        repeat(8) {
            ShimmerSurahListItem()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun ShimmerSurahListItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(40.dp)
                // --- PERBAIKAN DI SINI ---
                // Mengirimkan CircleShape secara eksplisit
                .shimmerEffect(shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.5f)
                    .shimmerEffect() // Memakai default shape
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.3f)
                    .shimmerEffect() // Memakai default shape
            )
        }
        Spacer(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(0.3f)
                .shimmerEffect() // Memakai default shape
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuranListLoadingShimmerPreview() {
    QuranListLoadingShimmer()
}
