package com.nafaskarya.muslimdaily.components.shared.loader

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.components.shared.shimmer.prayer.AyahListItemShimmer
import com.nafaskarya.muslimdaily.components.shared.shimmer.prayer.SurahInfoCardShimmer

@Composable
fun SurahScreenLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp),
        userScrollEnabled = false // Nonaktifkan scroll saat loading
    ) {
        item {
            SurahInfoCardShimmer(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        items(7) { // Tampilkan 7 placeholder ayat
            AyahListItemShimmer()
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.3f)
            )
        }
    }
}
