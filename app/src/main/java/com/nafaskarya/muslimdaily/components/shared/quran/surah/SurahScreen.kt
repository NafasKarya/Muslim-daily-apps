package com.nafaskarya.muslimdaily.components.shared.quran.surah

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.components.shared.loader.SurahScreenLoading
import com.nafaskarya.muslimdaily.components.widgets.quran.surah.AyahListItem
import com.nafaskarya.muslimdaily.components.widgets.quran.surah.SurahDetailHeader
import com.nafaskarya.muslimdaily.components.widgets.quran.surah.SurahInfoCard
// Import komponen shimmer yang baru
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import com.nafaskarya.muslimdaily.ui.viewmodel.SurahAlQuranViewModel

/**
 * Screen utama yang menampilkan detail surah dan daftar ayatnya.
 * Composable ini bersifat stateful, mengamati perubahan dari ViewModel.
 */
@Composable
fun SurahScreen(
    surahNumber: Int,
    viewModel: SurahAlQuranViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.surahDetailState.collectAsState()

    LaunchedEffect(key1 = surahNumber) {
        viewModel.fetchSurahDetail(surahNumber)
    }

    Scaffold(
        topBar = {
            SurahDetailHeader(
                onBackClick = onBackClick,
                onMoreClick = { /* TODO: Implement more options logic */ }
            )
        },
        containerColor = Color(0xFFFBFBFB)
    ) { paddingValues ->
        SurahScreenContent(
            modifier = Modifier.padding(paddingValues),
            state = state
        )
    }
}

/**
 * Composable yang bertanggung jawab untuk menampilkan konten utama layar
 * berdasarkan state yang diterima (Loading, Success, Error).
 * Composable ini bersifat stateless.
 */
@Composable
private fun SurahScreenContent(
    modifier: Modifier = Modifier,
    state: UiState<com.nafaskarya.muslimdaily.ui.data.quran.surah.SurahDetail>
) {
    when (state) {
        is UiState.Loading -> {
            // --- PERUBAHAN DI SINI ---
            // Mengganti CircularProgressIndicator dengan komponen shimmer yang utuh
            SurahScreenLoading(modifier = modifier)
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.message, color = Color.Red, textAlign = TextAlign.Center)
            }
        }
        is UiState.Success -> {
            val surahData = state.data
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                // Item untuk kartu informasi surah
                item {
                    SurahInfoCard(
                        surahDetail = surahData,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }

                // Item untuk daftar ayat
                itemsIndexed(surahData.ayahs) { index, ayah ->
                    AyahListItem(
                        surahNumber = surahData.number,
                        ayah = ayah
                    )
                    if (index < surahData.ayahs.lastIndex) {
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
}
