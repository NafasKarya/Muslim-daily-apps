package com.nafaskarya.muslimdaily.features.podcast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.features.podcast.data.model.PodcastEpisode


@Composable
fun PodcastEpisodeCard(
    episode: PodcastEpisode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(180.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = episode.imageRes),
            contentDescription = episode.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Text(
                text = episode.subtitle,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = episode.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                minLines = 2, // Agar tinggi konsisten
                lineHeight = 22.sp
            )
        }
    }
}


@Preview
@Composable
private fun PodcastEpisodeCardPreview() {
    val sampleEpisode = PodcastEpisode(
        title = "Kisah Cinta Suci Ali bin Abi Thalib",
        subtitle = "Podcast Eps. 1",
        imageRes = R.drawable.img_fajr
    )
    PodcastEpisodeCard(episode = sampleEpisode, onClick = {})
}