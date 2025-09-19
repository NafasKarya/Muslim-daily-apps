package com.nafaskarya.muslimdaily.components.widgets

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

/**
 * Modifier extension untuk menambahkan efek shimmer pada sebuah Composable.
 * Efek ini menggunakan gradien linear yang beranimasi untuk memberi kesan loading.
 */
private fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer_transition")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(1000)),
        label = "shimmer_offset_animation"
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned { size = it.size }
}

/**
 * Composable untuk menampilkan kartu waktu sholat dalam keadaan loading (shimmer).
 * Digunakan sebagai placeholder sebelum data asli tersedia.
 */
@Composable
internal fun ShimmerPrayerTimeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.6f))
    ) {
        Column {
            // Placeholder untuk bagian atas kartu
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(start = 24.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
            ) {
                Column(Modifier.align(Alignment.TopStart)) {
                    Spacer(modifier = Modifier.height(24.dp).width(150.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())
                    Spacer(Modifier.height(8.dp))
                    Spacer(modifier = Modifier.height(18.dp).width(180.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())
                }
                Spacer(modifier = Modifier.align(Alignment.CenterStart).height(36.dp).width(200.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())
            }

            // Placeholder untuk bagian bawah kartu
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(5) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp).width(50.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())
                        Spacer(Modifier.height(8.dp))
                        Spacer(modifier = Modifier.height(20.dp).width(40.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())
                    }
                }
            }
        }
    }
}