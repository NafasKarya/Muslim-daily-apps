package com.nafaskarya.muslimdaily.features.podcast.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.features.podcast.data.model.Podcast
import com.nafaskarya.muslimdaily.features.podcast.data.model.PodcastEpisode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// State class to hold all data for the screen
data class PodcastUiState(
    val featuredPodcasts: List<Podcast> = emptyList(),
    val forYouEpisodes: List<PodcastEpisode> = emptyList(),
    val topStoryEpisodes: List<PodcastEpisode> = emptyList(),
    val worshipCompanionEpisodes: List<PodcastEpisode> = emptyList()
)

class PodcastViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PodcastUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPodcasts()
    }

    private fun loadPodcasts() {
        // This is where you would fetch data from a repository (API or DB)
        // For now, we use dummy data.
        _uiState.value = PodcastUiState(
            featuredPodcasts = listOf(
                Podcast("Paling ngangenin", "Kumpulan Kisah Sahabat rasulullah", listOf(Color(0xFF1A0E38), Color(0xFF991B3F)), R.drawable.img_dhuha),
                Podcast("Paling seru", "Kisah-Kisah Hari Kiamat", listOf(Color(0xFF4A148C), Color(0xFFD50000)), R.drawable.img_dhuha)
            ),
            forYouEpisodes = List(5) { PodcastEpisode("Kisah Cinta Suci Ali", "Podcast Eps. 1", R.drawable.img_fajr) },
            topStoryEpisodes = List(5) { PodcastEpisode("Perang Badar", "Podcast Eps. 2", R.drawable.img_fajr) },
            worshipCompanionEpisodes = List(5) { PodcastEpisode("Keutamaan Tahajud", "Podcast Eps. 3", R.drawable.img_fajr) }
        )
    }

    // You can add functions to handle user events here
    fun onEpisodeClicked(episode: PodcastEpisode) {
        // TODO: Handle episode click, e.g., navigate to player screen
    }

    fun onFeaturedPodcastPlayed(podcast: Podcast) {
        // TODO: Handle play click for featured podcast
    }

    fun onViewAllForYouClicked() {
        // TODO: Handle "Lihat semua" click
    }

    fun onViewAllTopStoriesClicked() {
        // TODO: Handle "Lihat semua" click
    }

    fun onViewAllWorshipCompanionClicked() {
        // TODO: Handle "Lihat semua" click
    }
}