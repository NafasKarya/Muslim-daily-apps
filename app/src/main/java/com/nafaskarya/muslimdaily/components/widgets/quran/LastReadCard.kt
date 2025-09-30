// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/components/widgets/quran/LastReadCard.kt

package com.nafaskarya.muslimdaily.components.widgets.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.ui.data.quran.Surah

@Composable
fun LastReadCard(
    lastReadSurah: Surah,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.horizontalGradient(listOf(Color(0xFFF9D7B8), Color(0xFFFBE6D3))))
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text("Last Read", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
                Column {
                    Text(
                        text = lastReadSurah.englishName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Ayah No: 1", // Data ini masih statis
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LastReadCardPreview() {
    val dummySurah = Surah(1, "Al-Fatihah", "ٱلْفَاتِحَة", "Pembukaan", 7, "Mekah")
    LastReadCard(lastReadSurah = dummySurah, modifier = Modifier.padding(16.dp))
}