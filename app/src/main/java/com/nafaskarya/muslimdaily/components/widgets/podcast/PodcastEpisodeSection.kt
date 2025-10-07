package com.nafaskarya.muslimdaily.features.podcast.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.features.podcast.data.model.PodcastEpisode
import com.nafaskarya.muslimdaily.ui.theme.Inter // Pastikan path import ini benar

@Composable
fun PodcastEpisodeSection(
    title: String,
    episodes: List<PodcastEpisode>,
    onViewAllClicked: () -> Unit,
    onEpisodeClicked: (PodcastEpisode) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = title,
            onViewAllClicked = onViewAllClicked,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(episodes) { episode ->
                PodcastEpisodeCard(
                    episode = episode,
                    onClick = { onEpisodeClicked(episode) }
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    onViewAllClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Inter
        )
        Spacer(modifier = Modifier.weight(1f))
        ClickableText(
            text = AnnotatedString("Lihat semua"),
            onClick = { onViewAllClicked() },
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun PodcastEpisodeSectionPreview() {
    val sampleEpisodes = List(5) {
        PodcastEpisode("Kisah Cinta Suci Ali", "Podcast Eps. ${it + 1}", R.drawable.img_fajr)
    }
    PodcastEpisodeSection(
        title = "Buat Kamu Hari Ini",
        episodes = sampleEpisodes,
        onViewAllClicked = {},
        onEpisodeClicked = {}
    )
}