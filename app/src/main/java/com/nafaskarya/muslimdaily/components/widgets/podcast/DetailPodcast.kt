package com.nafaskarya.muslimdaily.features.podcast.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable // <-- PASTIKAN IMPORT INI ADA
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Share
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R

// --- LANGKAH 1: Tambahkan parameter onNavigateToPlaying ---
@Composable
fun DetailPodcastScreen(
    onBackClick: () -> Unit = {},
    onNavigateToPlaying: () -> Unit // <-- TAMBAHKAN PARAMETER INI
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        CustomTopAppBar(onBackClick = onBackClick)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.img_fajr),
                contentDescription = "Podcast Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))
            PodcastHeader()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Boost your vocabulary effortlessly with Ruby Sterling as she explores words, their meanings, and real-life usage. Perfect for language lovers looking to learn, remember...See More",
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "21K listeners • 11 Episodes available",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))
            ActionButtons()
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Full Series",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Daftar Episode
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // --- LANGKAH 2: Teruskan aksi klik ke setiap EpisodeItem ---
                EpisodeItem(
                    title = "Communication Skill",
                    details = "25:50 - 5 hours ago",
                    onClick = onNavigateToPlaying // <-- SAMBUNGKAN DI SINI
                )
                EpisodeItem(
                    title = "Advanced Vocabulary",
                    details = "30:15 - 1 day ago",
                    onClick = onNavigateToPlaying // <-- SAMBUNGKAN DI SINI
                )
                EpisodeItem(
                    title = "Idioms and Phrases",
                    details = "28:40 - 2 days ago",
                    onClick = onNavigateToPlaying // <-- SAMBUNGKAN DI SINI
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
private fun CustomTopAppBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.border(1.dp, Color.LightGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = "Podcast Details",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(48.dp))
    }
}


@Composable
private fun PodcastHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Learn vocabulary with ...",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ruby Sterling",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = "Verified",
                    tint = Color(0xFF007BFF),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA500))
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Composable
private fun ActionButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val iconButtonModifier = Modifier
            .size(52.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))

        IconButton(onClick = { /*TODO*/ }, modifier = iconButtonModifier) {
            Icon(Icons.Outlined.BookmarkBorder, contentDescription = "Bookmark", tint = Color.Gray)
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Follow Show", fontSize = 16.sp)
        }

        IconButton(onClick = { /*TODO*/ }, modifier = iconButtonModifier) {
            Icon(Icons.Outlined.Share, contentDescription = "Share", tint = Color.Gray)
        }
    }
}

// --- LANGKAH 3: Modifikasi EpisodeItem agar bisa diklik ---
@Composable
private fun EpisodeItem(title: String, details: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // <-- TAMBAHKAN MODIFIER INI
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_dhuha),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = details,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = onClick, // Tombol play juga bisa diarahkan ke aksi yang sama
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA500).copy(alpha = 0.1f))
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play Episode",
                tint = Color(0xFFFFA500)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailPodcastScreenPreview() {
    MaterialTheme {
        // Preview diupdate untuk parameter baru
        DetailPodcastScreen(onNavigateToPlaying = {})
    }
}