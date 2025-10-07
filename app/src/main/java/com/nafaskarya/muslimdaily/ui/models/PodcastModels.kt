package com.nafaskarya.muslimdaily.features.podcast.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Podcast(
    val title: String,
    val subtitle: String,
    val gradientColors: List<Color>,
    @DrawableRes val imageRes: Int
)

data class PodcastEpisode(
    val title: String,
    val subtitle: String,
    @DrawableRes val imageRes: Int
)