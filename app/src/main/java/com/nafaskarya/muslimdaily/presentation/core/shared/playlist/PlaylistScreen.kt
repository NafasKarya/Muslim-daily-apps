package com.nafaskarya.muslimdaily.presentation.core.shared.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.R

// Data Dummy untuk List Lagu
data class TrackItem(
    val id: Int,
    val title: String,
    val subtitle: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    onBackClick: () -> Unit
) {
    val tracks = List(10) {
        TrackItem(it, "Kajian A dan B", "Kajian A dan B : 3:55")
    }

    // Gambar utama (bisa diganti URL atau Resource ID)
    val coverImage = R.drawable.img_onboarding // Pastikan ada di drawable

    Scaffold(
        containerColor = Color.Black, // Background dasar hitam
        topBar = {
            // Top Bar Transparan
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {

            // 1. BACKGROUND BLUR & GRADIENT
            // Bagian ini membuat efek blur warna-warni di belakang cover
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Tinggi area blur
            ) {
                AsyncImage(
                    model = coverImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(50.dp) // Efek Blur Kuat
                        .drawWithCache {
                            // Gradient Hitam dari bawah ke atas agar menyatu dengan background
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.2f), Color.Black),
                                startY = 0f,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient)
                            }
                        }
                )
            }

            // 2. KONTEN SCROLLING (Cover + Judul + List)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp), // Padding bawah agar tidak mentok
                contentPadding = paddingValues
            ) {

                // --- HEADER SECTION ---
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        // Cover Image Tajam
                        AsyncImage(
                            model = coverImage,
                            contentDescription = "Album Art",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(220.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Judul Playlist
                        Text(
                            text = "Berubah Menjadi Baik Yuk,\ndengan rasa syukur kita",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                lineHeight = 28.sp
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Tombol Aksi (Wave, Play, Bookmark)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Tombol 1: Wave/Sound (Outline)
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(1.dp, Color.Gray, CircleShape)
                                    .background(Color.White.copy(alpha = 0.1f), CircleShape)
                            ) {
                                Icon(Icons.Default.GraphicEq, contentDescription = "Preview", tint = Color.White)
                            }

                            // Tombol 2: Play (Outline Triangle)
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(1.dp, Color.Gray, CircleShape)
                                    .background(Color.White.copy(alpha = 0.1f), CircleShape)
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White)
                            }

                            // Tombol 3: Bookmark (Filled)
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color(0xFF3E3E3E), CircleShape) // Warna abu gelap solid
                            ) {
                                Icon(Icons.Default.Bookmark, contentDescription = "Save", tint = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }

                // --- LIST ITEM SECTION ---
                items(tracks) { track ->
                    PlaylistItemRow(track)
                }
            }
        }
    }
}

@Composable
fun PlaylistItemRow(track: TrackItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Thumbnail Kecil (Abu-abu di desain)
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray) // Ganti dengan AsyncImage jika ada gambar per item
        ) {
            // Jika pakai gambar:
            // AsyncImage(model = ..., contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Text Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = track.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = track.subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                ),
                maxLines = 1
            )
        }

        // Menu Option (Titik Tiga)
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = Color.White
            )
        }
    }
}