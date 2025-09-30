// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/ui/quran/components/QuranLoadingShimmer.kt

package com.nafaskarya.muslimdaily.components.shared.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.ui.components.shared.shimmerEffect

@Composable
fun QuranListLoadingShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(130.dp).shimmerEffect()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp).width(120.dp).shimmerEffect())
            Spacer(modifier = Modifier.height(40.dp).width(180.dp).shimmerEffect())
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
        Spacer(modifier = Modifier.size(40.dp).clip(CircleShape).shimmerEffect())
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Spacer(modifier = Modifier.height(16.dp).fillMaxWidth(0.5f).shimmerEffect())
            Spacer(modifier = Modifier.height(12.dp).fillMaxWidth(0.3f).shimmerEffect())
        }
        Spacer(modifier = Modifier.height(24.dp).fillMaxWidth(0.2f).shimmerEffect())
    }
}

@Preview(showBackground = true)
@Composable
private fun QuranListLoadingShimmerPreview() {
    QuranListLoadingShimmer()
}