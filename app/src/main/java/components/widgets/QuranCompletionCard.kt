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
import androidx.compose.ui.layout.ContentScale
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
        // Box berfungsi sebagai container untuk menumpuk elemen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            // 1. Gambar sebagai Latar Belakang Utama, menutupi seluruh Box
            Image(
                painter = painterResource(id = AppImages.TrackQuran),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Memastikan gambar mengisi area tanpa distorsi
            )

            // 2. Lapisan Scrim (overlay gelap) agar teks mudah dibaca
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)) // Sesuaikan alpha untuk tingkat kegelapan
            )

            // 3. Teks ditampilkan di lapisan paling atas
            Column(
                modifier = Modifier
                    .fillMaxSize() // Teks mengisi seluruh Box agar alignment berfungsi
                    .padding(horizontal = 20.dp, vertical = 16.dp),
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