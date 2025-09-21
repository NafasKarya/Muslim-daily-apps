package com.nafaskarya.muslimdaily.components.shared.shimmer

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Komponen Shimmer utama yang akan dipanggil
@Composable
fun ShimmerPrayerTimeCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
    ) {
        // Placeholder untuk Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(12.dp))
        // Placeholder untuk Card utama
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .shimmerEffect()
        )
    }
}

// Modifier custom untuk membuat efek shimmer
fun Modifier.shimmerEffect(): Modifier = composed {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer_transition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
        ),
        label = "shimmer_translate_animation"
    )

    background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        ),
        shape = RoundedCornerShape(8.dp)
    )
}