// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/ui/quran/QuranScreen.kt

package com.nafaskarya.muslimdaily.components.shared.quran

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nafaskarya.muslimdaily.components.shared.shimmer.quran.QuranListLoadingShimmer
import com.nafaskarya.muslimdaily.components.widgets.quran.QuranListContent
import com.nafaskarya.muslimdaily.ui.data.quran.Surah // Pastikan import ini ada
import com.nafaskarya.muslimdaily.ui.repository.quran.AlQuranRepository
import com.nafaskarya.muslimdaily.ui.utils.network.RetrofitClient
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import com.nafaskarya.muslimdaily.ui.viewmodel.AlQuranViewModel
import com.nafaskarya.muslimdaily.ui.viewmodel.AlQuranViewModelFactory


@Composable
fun QuranScreen(
    navController: NavController,
    viewModel: AlQuranViewModel = viewModel(
        factory = AlQuranViewModelFactory(
            AlQuranRepository(
                RetrofitClient.alQuranApiService
            )
        )
    )
) {
    val surahsState by viewModel.surahsState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = surahsState) {
            is UiState.Loading -> {
                QuranListLoadingShimmer()
            }

            is UiState.Success<*> -> {
                QuranListContent(
                    // --- PERBAIKAN: Lakukan safe cast pada state.data ---
                    surahs = (state.data as? List<Surah>) ?: emptyList(),
                    onMenuClick = { /* TODO: Buka navigation drawer */ },
                    onSearchClick = { /* TODO: Buka halaman search */ },
                    onSurahClick = { surah ->
                        navController.navigate("surah_detail/${surah.number}")
                    }
                )
            }

            is UiState.Error -> {
                Text(
                    text = "Gagal memuat data: ${state.message}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}