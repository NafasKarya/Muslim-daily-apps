package com.nafaskarya.muslimdaily.components.shared.quran

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
// import androidx.compose.material3.Text // Import ini tidak lagi diperlukan untuk error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nafaskarya.muslimdaily.ui.components.NoInternetScreen // <-- 1. TAMBAHKAN IMPORT INI
import com.nafaskarya.muslimdaily.components.shared.shimmer.quran.QuranListLoadingShimmer
import com.nafaskarya.muslimdaily.components.widgets.quran.QuranListContent
import com.nafaskarya.muslimdaily.ui.data.quran.Surah
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
                    surahs = (state.data as? List<Surah>) ?: emptyList(),
                    onMenuClick = { /* TODO: Buka navigation drawer */ },
                    onSearchClick = { /* TODO: Buka halaman search */ },
                    onSurahClick = { surah ->
                        navController.navigate("surah_detail/${surah.number}")
                    }
                )
            }

            // --- 2. UBAH BAGIAN INI ---
            is UiState.Error -> {
                NoInternetScreen(onRetry = viewModel::fetchSurahs)
            }
        }
    }
}