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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppImages
import com.valentinilk.shimmer.shimmer // <-- 1. Import shimmer

@Composable
fun LastReadCard(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false, // <-- 2. Tambahkan parameter isLoading
    surahName: String = "Al-Fatihah",
    ayahNumber: Int = 1
) {
    // 3. Gunakan if-else untuk menampilkan Shimmer atau konten asli
    if (isLoading) {
        ShimmerLastReadCard(modifier = modifier)
    } else {
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
                    .height(140.dp)
            ) {
                Image(
                    painter = painterResource(id = AppImages.TrackQuran),
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
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
}

/**
 * Komponen private untuk menampilkan placeholder (kerangka) UI dengan efek shimmer.
 */
@Composable
private fun ShimmerLastReadCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            // 4. Terapkan modifier .shimmer() pada container placeholder
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .shimmer()
                .background(Color.Gray.copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Placeholder untuk "Last Read"
                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray.copy(alpha = 0.5f))
                )

                // Placeholder untuk Surah dan Ayah
                Column {
                    Box(
                        modifier = Modifier
                            .height(22.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray.copy(alpha = 0.5f))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .height(15.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}


// 5. Buat dua preview untuk melihat kedua state (loading & loaded)
@Preview(showBackground = true, name = "Last Read Card - Loading")
@Composable
private fun LastReadCardLoadingPreview() {
    LastReadCard(isLoading = true)
}

@Preview(showBackground = true, name = "Last Read Card - Loaded")
@Composable
private fun LastReadCardLoadedPreview() {
    LastReadCard(isLoading = false)
}