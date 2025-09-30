// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/ui/quran/SurahScreen.kt

package com.nafaskarya.muslimdaily.components.shared.quran.surah

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R

// --- Data Model Sederhana ---
data class Ayah(
    val numberInSurah: Int,
    val arabicText: String,
    val englishTranslation: String
)

// --- Composable Utama untuk Screen ---
@Composable
fun SurahScreen() {
    // Data Dummy untuk UI
    val surahName = "Al-Fatihah"
    val surahTranslation = "The Opener"
    val ayahCount = 7
    val ayahs = listOf(
        Ayah(1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "In the name of Allah, the Entirely Merciful, the Especially Merciful."),
        Ayah(2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "[All] praise is [due] to Allah, Lord of the worlds -"),
        Ayah(3, "الرَّحْمَٰنِ الرَّحِيمِ", "The Entirely Merciful, the Especially Merciful,"),
        Ayah(4, "مَالِكِ يَوْمِ الدِّينِ", "Sovereign of the Day of Recompense."),
        Ayah(5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "It is You we worship and You we ask for help."),
        Ayah(6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Guide us to the straight path -"),
        Ayah(7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFBFBFB) // Warna background utama
    ) {
        val statusBarPadding = WindowInsets.statusBars.asPaddingValues()

        LazyColumn(
            contentPadding = PaddingValues(
                top = statusBarPadding.calculateTopPadding() + 16.dp,
                bottom = 16.dp
            )
        ) {
            // Header Utama (Back, Title, More)
            item {
                SurahDetailHeader(
                    onBackClick = { /* TODO */ },
                    onMoreClick = { /* TODO */ },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Kartu Informasi Surah
            item {
                SurahInfoCard(
                    surahName = surahName,
                    surahTranslation = surahTranslation,
                    ayahCount = ayahCount,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Daftar Ayat
            itemsIndexed(ayahs) { index, ayah ->
                AyahListItem(
                    surahNumber = 1,
                    ayah = ayah
                )
                if (index < ayahs.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                        thickness = 1.dp,
                        color = Color.LightGray.copy(alpha = 0.3f)
                    )
                }
            }
        }
    }
}

// --- Komponen-komponen UI ---

@Composable
private fun SurahDetailHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "QURAN",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        IconButton(onClick = onMoreClick) {
            Icon(Icons.Default.MoreVert, contentDescription = "More Options")
        }
    }
}

@Composable
private fun SurahInfoCard(
    modifier: Modifier = Modifier,
    surahName: String,
    surahTranslation: String,
    ayahCount: Int
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
                Text(text = surahName, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = surahTranslation, fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$ayahCount Ayahs", fontSize = 14.sp, color = Color.Gray)
            }

            Image(
                painter = painterResource(id = R.drawable.ic_bismillah_arabic),
                contentDescription = "Bismillah",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
    }
}

@Composable
private fun AyahListItem(
    surahNumber: Int,
    ayah: Ayah
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        // Baris nomor ayat dan menu
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$surahNumber:${ayah.numberInSurah}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Ayah Options", tint = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Teks Arab
        Text(
            text = ayah.arabicText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 24.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Teks Terjemahan
        Text(
            text = ayah.englishTranslation,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.DarkGray,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Aksi (Play, Bookmark, Share)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconColor = Color.Gray
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Play Audio", tint = iconColor)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.BookmarkBorder, contentDescription = "Bookmark Ayah", tint = iconColor)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Share, contentDescription = "Share Ayah", tint = iconColor)
            }
        }
    }
}


// --- Preview untuk Pengecekan di Android Studio ---
@Preview(showBackground = true)
@Composable
private fun SurahScreenPreview() {
    SurahScreen()
}