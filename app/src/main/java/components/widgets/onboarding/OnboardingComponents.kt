package com.nafaskarya.muslimdaily.onboarding.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.onboarding.data.OnboardingPage
import kotlinx.coroutines.delay

@Composable
internal fun OnboardingPageContent(page: OnboardingPage) {
    // Gunakan Box sebagai container utama untuk menumpuk elemen
    Box(modifier = Modifier.fillMaxSize()) {
        // Jadikan Image sebagai latar belakang layar penuh
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null, // Gambar hanya sebagai dekorasi
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Agar gambar menutupi layar tanpa distorsi
        )

        // Tambahkan "shadow" menggunakan gradient dari transparan ke hitam
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black
                        ),
                        // Mulai gradient dari sekitar 30% bagian atas layar
                        startY = LocalConfiguration.current.screenHeightDp.toFloat() * 0.3f
                    )
                )
        )

        // Tampilkan konten teks di atas gambar dan gradient
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(bottom = 180.dp), // Beri jarak dari bawah
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom // Posisikan teks di bagian bawah
        ) {
            Text(
                text = page.title,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = page.subtitle,
                color = Color.White.copy(alpha = 0.8f), // Warna lebih kontras
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingBottomSection(
    pagerState: PagerState,
    onNextClicked: () -> Unit,
    isPressed: Boolean,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "ProgressAnimation")

    val isLastPage = pagerState.currentPage == pagerState.pageCount - 1

    LaunchedEffect(key1 = pagerState.currentPage) {
        while (pagerState.isScrollInProgress) {
            delay(100)
        }

        if (isLastPage) {
            progress = 0f
        } else {
            progress = 0f
            while (progress < 1f) {
                if (pagerState.isScrollInProgress) {
                    progress = 0f
                    break
                }

                if (!isPressed) {
                    progress += 0.01f
                }

                delay(50)
            }

            if (progress >= 1f) {
                onNextClicked()
            }
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (isLastPage) {
            Button(
                onClick = onNextClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Mulai Sekarang",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicators(
                    pageCount = pagerState.pageCount,
                    currentPage = pagerState.currentPage
                )

                CircularProgressButton(
                    progress = animatedProgress,
                    onClick = onNextClicked
                )
            }
        }
    }
}

@Composable
private fun PageIndicators(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(if (isSelected) 24.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else Color.Gray)
            )
        }
    }
}

@Composable
private fun CircularProgressButton(
    progress: Float,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(70.dp)
    ) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
            strokeWidth = 2.dp,
            trackColor = Color.DarkGray
        )
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next Page",
                tint = Color.Black
            )
        }
    }
}