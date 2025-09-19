package com.nafaskarya.muslimdaily.components.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppImages

@Composable
fun LastReadCard(
    modifier: Modifier = Modifier,
    surahName: String = "Al-Fatihah",
    ayahNumber: Int = 1
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp) // tinggi card tetap
                .background(Color(0xFF508747)) // hijau solid
        ) {
            // Gambar Qur'an (Track) - agak ke bawah & kiri
            Image(
                painter = painterResource(id = AppImages.TrackQuran),
                contentDescription = "Track Quran Illustration",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-8).dp, y = 6.dp) // geser ke kiri & bawah
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )

            // Teks tetap di sisi kiri
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 20.dp, top = 16.dp, bottom = 20.dp, end = 120.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Last Read",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )

                Column {
                    Text(
                        text = surahName,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ayah No: $ayahNumber",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LastReadCardPreview() {
    LastReadCard()
}
