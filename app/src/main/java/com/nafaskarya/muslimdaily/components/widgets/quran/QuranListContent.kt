// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/components/widgets/quran/QuranListContent.kt

package com.nafaskarya.muslimdaily.components.widgets.quran

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.ui.data.quran.Surah

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuranListContent(
    surahs: List<Surah>,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSurahClick: (Surah) -> Unit // --- 1. Tambahkan parameter lambda untuk klik surah ---
) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    var selectedTab by remember { mutableStateOf("Surah") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = statusBarPadding.calculateTopPadding() + 12.dp,
            bottom = 12.dp
        )
    ) {
        item {
            QuranHeader(
                onMenuClick = onMenuClick,
                onSearchClick = onSearchClick
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (surahs.isNotEmpty()) {
                LastReadCard(lastReadSurah = surahs.first())
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        stickyHeader {
            SurahListHeader(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                modifier = Modifier.background(Color.White)
            )
        }

        itemsIndexed(surahs) { index, surah ->
            // --- 2. Teruskan aksi klik ke setiap SurahListItem ---
            SurahListItem(
                surah = surah,
                onSurahClick = onSurahClick
            )

            if (index < surahs.lastIndex) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray.copy(alpha = 0.4f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun QuranListContentPreview() {
    val dummySurah = Surah(1, "Al-Fatihah", "ٱلْفَاتِحَة", "Pembukaan", 7, "Mekah")
    QuranListContent(
        surahs = List(15) { dummySurah },
        onMenuClick = {},
        onSearchClick = {},
        onSurahClick = {} // --- 3. Perbarui preview dengan lambda kosong ---
    )
}