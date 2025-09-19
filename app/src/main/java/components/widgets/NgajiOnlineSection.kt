package com.nafaskarya.muslimdaily.components.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppImages

// Data class untuk video ngaji
data class LiveVideo(
    val title: String,
    @DrawableRes val thumbnailRes: Int,
    val viewers: String,
    val isLive: Boolean = false
)

@Composable
fun NgajiOnlineSection() {
    val videos = listOf(
        LiveVideo("Kajian Subuh", AppImages.Reminder, "3.6K viewers", isLive = true),
        LiveVideo("Kajian Dzuhur", AppImages.Reminder, "2.1K viewers", isLive = true),
        LiveVideo("Kajian Maghrib", AppImages.Reminder, "1.8K viewers", isLive = true)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp) // ⬅️ hanya vertical padding
    ) {
        // Header bagian atas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // ⬅️ padding khusus header
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ngajii Online",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1ABC9C)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // List horizontal video (full bleed, tapi tetap ada padding kiri/kanan)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp), // ⬅️ biar ga ke-crop
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(videos) { video ->
                LiveVideoCard(video)
            }
        }
    }
}

@Composable
fun LiveVideoCard(video: LiveVideo) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .height(140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Thumbnail
            Image(
                painter = painterResource(id = video.thumbnailRes),
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Badge LIVE (pojok kanan atas)
            if (video.isLive) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.Red)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "LIVE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Badge viewers (pojok kiri bawah)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1ABC9C))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = video.viewers,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
