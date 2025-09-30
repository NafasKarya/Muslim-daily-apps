// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/ui/quran/QuranScreen.kt

package com.nafaskarya.muslimdaily.components.shared.quran

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth // <-- 1. Tambahkan import ini
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // <-- 2. Tambahkan import ini
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nafaskarya.muslimdaily.components.shared.shimmer.QuranListLoadingShimmer
import com.nafaskarya.muslimdaily.components.widgets.quran.QuranListContent

import com.nafaskarya.muslimdaily.ui.repository.quran.AlQuranRepository
import com.nafaskarya.muslimdaily.ui.utils.network.RetrofitClient
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import com.nafaskarya.muslimdaily.ui.viewmodel.AlQuranViewModel
import com.nafaskarya.muslimdaily.ui.viewmodel.AlQuranViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            QuranTopAppBar(
                onMenuClick = { /* TODO: Buka navigation drawer */ },
                onSearchClick = { /* TODO: Buka halaman search */ }
            )
        },
        containerColor = Color(0xFFF7F8FA) // Sekarang sudah valid
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = surahsState) {
                is UiState.Loading -> {
                    QuranListLoadingShimmer()
                }
                is UiState.Success -> {
                    QuranListContent(surahs = state.data)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuranTopAppBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "QURAN",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(), // Sekarang sudah valid
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, "Menu")
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, "Search")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF7F8FA)) // Sekarang sudah valid
    )
}