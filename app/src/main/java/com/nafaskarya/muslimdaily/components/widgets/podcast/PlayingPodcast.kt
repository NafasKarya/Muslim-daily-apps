package com.nafaskarya.muslimdaily.features.podcast.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R
import kotlin.random.Random

@Composable
fun PlayingPodcastScreen(
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top + WindowInsetsSides.Bottom))
    ) {
        // 1. Top Bar
        TopBar(onBackClick = onBackClick)

        // --- PERUBAHAN DI SINI ---
        // 2. Player Card
        Column(
            modifier = Modifier
                .weight(1f) // `weight` akan mendorong PlaybackControls ke bawah
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            // Menghapus Arrangement.Center agar konten tidak dipaksa ke tengah vertikal
        ) {
            // Menambahkan Spacer untuk memberi jarak dari TopBar
            Spacer(modifier = Modifier.height(32.dp))
            PlayerCard()
        }

        // 3. Playback Controls
        PlaybackControls()
    }
}

@Composable
private fun TopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tombol Kembali dengan border lingkaran
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.border(1.dp, Color.LightGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }

        // Judul di tengah
        Text(
            text = "On Play",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        // Tombol Download dengan border lingkaran
        IconButton(
            onClick = { /* TODO: Download action */ },
            modifier = Modifier.border(1.dp, Color.LightGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = "Download"
            )
        }
    }
}

@Composable
private fun PlayerCard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_fajr), // Ganti dengan gambar Anda
            contentDescription = "Podcast Cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(24.dp))
        )

        Spacer(modifier = Modifier.height(32.dp))

        AudioWaveform()

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "06:11", fontSize = 12.sp, color = Color.Gray)
            Text(text = "12:08", fontSize = 12.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Episode 04 – Easy Vocabulary Learning",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun AudioWaveform() {
    val darkBarColor = MaterialTheme.colorScheme.onSurface
    val lightBarColor = Color.LightGray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        val totalBars = 60
        val playedBars = 35

        for (i in 1..totalBars) {
            val barHeight = Random.nextInt(10, 50).dp
            val barColor = if (i <= playedBars) darkBarColor else lightBarColor
            WaveformBar(height = barHeight, color = barColor)
        }
    }
}

@Composable
private fun WaveformBar(height: Dp, color: Color) {
    Box(
        modifier = Modifier
            .width(3.dp)
            .height(height)
            .clip(CircleShape)
            .background(color)
    )
}


@Composable
private fun PlaybackControls() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val iconColor = MaterialTheme.colorScheme.onSurfaceVariant

        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Loop, contentDescription = "Repeat", tint = iconColor)
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Skip Previous", tint = iconColor, modifier = Modifier.size(40.dp))
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(64.dp)
                .background(Color(0xFFFFA500), CircleShape)
        ) {
            Icon(Icons.Default.Pause, contentDescription = "Pause", tint = Color.White, modifier = Modifier.size(36.dp))
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Skip Next", tint = iconColor, modifier = Modifier.size(40.dp))
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More Options", tint = iconColor)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlayingPodcastScreenPreview() {
    MaterialTheme {
        PlayingPodcastScreen()
    }
}