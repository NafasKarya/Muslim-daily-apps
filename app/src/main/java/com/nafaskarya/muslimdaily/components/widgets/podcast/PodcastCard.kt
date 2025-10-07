package com.nafaskarya.muslimdaily.features.podcast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.features.podcast.data.model.Podcast

@Composable
fun PodcastCard(
    podcast: Podcast,
    onCardClicked: () -> Unit, // Parameter baru untuk handle klik pada seluruh kartu
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(380.dp)
            .height(180.dp)
            .clickable(onClick = onCardClicked) // Membuat seluruh kartu bisa di-klik
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = podcast.gradientColors
                    )
                )
        ) {
            Image(
                painter = painterResource(id = podcast.imageRes),
                contentDescription = podcast.subtitle,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(165.dp)
                    .align(Alignment.BottomEnd)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 16.dp, top = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = podcast.title,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = podcast.subtitle,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                }

                // Tombol play ini tetap ada, bisa digunakan untuk aksi lain (misal: play langsung)
                // Namun, klik utamanya sekarang ada di Card
                PlayButton(
                    onClick = { /* TODO: Aksi spesifik tombol play, jika beda */ },
                    gradientColors = podcast.gradientColors
                )
            }
        }
    }
}

@Composable
private fun PlayButton(
    onClick: () -> Unit,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier
) {
    val borderGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF2E31E5), Color(0xFFBA1C1C))
    )
    val innerBackgroundColor = gradientColors.first().copy(alpha = 0.8f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(50))
            .background(borderGradient)
            .clickable(onClick = onClick) // Tombol play tetap bisa di-klik terpisah
            .padding(1.dp)
            .background(
                color = innerBackgroundColor,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play Icon",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "dengarkan",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}