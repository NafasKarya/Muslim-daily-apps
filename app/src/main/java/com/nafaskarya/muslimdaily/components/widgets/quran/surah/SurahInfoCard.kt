package com.nafaskarya.muslimdaily.components.widgets.quran.surah

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import com.nafaskarya.muslimdaily.ui.data.quran.surah.SurahDetail

@Composable
fun SurahInfoCard(
    modifier: Modifier = Modifier,
    surahDetail: SurahDetail
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF9E8D9),
                        Color(0xFFFEF8F2)
                    )
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = surahDetail.arabicName, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = surahDetail.englishNameTranslation, fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${surahDetail.numberOfAyahs} Ayat", fontSize = 14.sp, color = Color.Gray)
            }
            Image(
                painter = painterResource(id = R.drawable.ic_bismillah_arabic),
                contentDescription = "Bismillah",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SurahInfoCardPreview() {
    val dummySurahDetail = SurahDetail(
        number = 1,
        arabicName = "Al-Fatihah",
        englishName = "The Opener",
        englishNameTranslation = "Pembukaan",
        numberOfAyahs = 7.toString(),
        revelationType = "Mekah",
        ayahs = emptyList() // ayahs tidak ditampilkan di sini, jadi bisa kosong
    )
    SurahInfoCard(
        surahDetail = dummySurahDetail,
        modifier = Modifier.padding(16.dp)
    )
}
