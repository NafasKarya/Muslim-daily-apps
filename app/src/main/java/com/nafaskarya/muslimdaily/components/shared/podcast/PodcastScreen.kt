package com.nafaskarya.muslimdaily.features.podcast.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.features.podcast.data.model.Podcast
import com.nafaskarya.muslimdaily.features.podcast.data.model.PodcastEpisode
import com.nafaskarya.muslimdaily.features.podcast.ui.components.PodcastCard
import com.nafaskarya.muslimdaily.features.podcast.ui.components.PodcastEpisodeSection
import com.nafaskarya.muslimdaily.features.podcast.ui.viewmodel.PodcastUiState
import com.nafaskarya.muslimdaily.features.podcast.ui.viewmodel.PodcastViewModel
import com.nafaskarya.muslimdaily.ui.theme.Inter

// Entry point with ViewModel
@Composable
fun PodcastScreen(
    modifier: Modifier = Modifier,
    viewModel: PodcastViewModel = viewModel(),
    // Parameter baru untuk menangani navigasi ke halaman detail
    onNavigateToDetail: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Meneruskan aksi navigasi ke Composable di bawahnya
    PodcastScreenContent(
        modifier = modifier,
        state = uiState,
        onPodcastCardClicked = onNavigateToDetail,
        onEpisodeCardClicked = onNavigateToDetail, // Diasumsikan klik episode juga ke detail
        onViewAllClicked = { /* TODO: Handle "Lihat semua" */ }
    )
}

// Stateless Composable for UI structure
@Composable
fun PodcastScreenContent(
    modifier: Modifier = Modifier,
    state: PodcastUiState,
    // Parameter diperbarui untuk fokus pada aksi UI (klik), bukan logika ViewModel
    onPodcastCardClicked: () -> Unit,
    onEpisodeCardClicked: () -> Unit,
    onViewAllClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Adjust for status bar
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        Spacer(modifier = Modifier.height(16.dp))

        ScreenHeader(modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.featuredPodcasts) { podcast ->
                // Menggunakan parameter onCardClicked yang baru
                PodcastCard(
                    podcast = podcast,
                    onCardClicked = onPodcastCardClicked
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        PodcastEpisodeSection(
            title = "Buat Kamu hari ini",
            episodes = state.forYouEpisodes,
            onViewAllClicked = onViewAllClicked,
            onEpisodeClicked = { onEpisodeCardClicked() }
        )

        Spacer(modifier = Modifier.height(32.dp))

        PodcastEpisodeSection(
            title = "Playlist Paling Greget",
            episodes = state.topStoryEpisodes,
            onViewAllClicked = onViewAllClicked,
            onEpisodeClicked = { onEpisodeCardClicked() }
        )

        Spacer(modifier = Modifier.height(32.dp))

        PodcastEpisodeSection(
            title = "Nemenin Ibadah Kamu",
            episodes = state.worshipCompanionEpisodes,
            onViewAllClicked = onViewAllClicked,
            onEpisodeClicked = { onEpisodeCardClicked() }
        )

        // Spacer at the bottom for better scroll feel
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ScreenHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Dengarkan Podcast",
            fontFamily = Inter,
            fontWeight = FontWeight.Light,
            fontSize = 26.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Dimana pun Kamu suka",
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}