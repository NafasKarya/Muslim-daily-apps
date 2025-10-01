package com.nafaskarya.muslimdaily.components.shared.shimmer.prayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AyahListItemShimmer(modifier: Modifier = Modifier) {
    val brush = rememberShimmerBrush()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        // Baris nomor ayat dan menu
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .width(50.dp)
                    .background(brush, RoundedCornerShape(8.dp))
            )
            Spacer(
                modifier = Modifier
                    .size(24.dp)
                    .background(brush, CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Teks Arab (placeholder)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(
                modifier = Modifier
                    .height(28.dp)
                    .fillMaxWidth(0.9f)
                    .background(brush, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(
                modifier = Modifier
                    .height(28.dp)
                    .fillMaxWidth(0.7f)
                    .background(brush, RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Teks Terjemahan (placeholder)
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(1f)
                    .background(brush, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.8f)
                    .background(brush, RoundedCornerShape(8.dp))
            )
        }
    }
}
