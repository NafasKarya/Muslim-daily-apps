package com.nafaskarya.muslimdaily.components.shared.kitab

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R // Pastikan ini adalah R dari package Anda
import kotlinx.coroutines.delay

data class KitabPage(
    val title: String,
    val content: String,
    val imageResId: Int
)

val kitabPages = listOf(
    KitabPage(
        title = "Misteri Suku Waq Waq",
        content = "The exact meaning of the term ifrit in the earliest sources is difficult to determine...",
        imageResId = R.drawable.img_fajr
    ),
    KitabPage(
        title = "Kisah Nabi Musa",
        content = "Ini adalah halaman kedua yang berisi kisah Nabi Musa...",
        imageResId = R.drawable.img_dhuha
    ),
    KitabPage(
        title = "Sejarah Andalusia",
        content = "Ini adalah halaman ketiga yang menceritakan tentang sejarah Andalusia...",
        imageResId = R.drawable.img_tahajud
    )
)

@Composable
fun AnimatedSoundButton(
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    val cornerRadius by animateDpAsState(
        targetValue = if (isExpanded) 20.dp else 50.dp,
        animationSpec = tween(durationMillis = 400),
        label = "cornerRadius"
    )

    Row(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(durationMillis = 400))
            .height(40.dp)
            .clip(RoundedCornerShape(size = cornerRadius))
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            contentDescription = "Sound Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandHorizontally(
                animationSpec = tween(durationMillis = 400),
                expandFrom = Alignment.Start
            ),
            exit = shrinkHorizontally(
                animationSpec = tween(durationMillis = 400),
                shrinkTowards = Alignment.Start
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Aktifkan Suara",
                    color = Color.White,
                    fontSize = 12.sp,
                    maxLines = 1,
                    softWrap = false
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KitabScreen() {
    val pagerState = rememberPagerState(pageCount = { kitabPages.size })
    var isButtonExpanded by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000L)
        isButtonExpanded = false
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(
                targetState = pagerState.currentPage,
                animationSpec = tween(durationMillis = 500),
                modifier = Modifier.fillMaxSize(),
                label = "ImageCrossfade"
            ) { page ->
                Image(
                    painter = painterResource(id = kitabPages[page].imageResId),
                    contentDescription = "Background Image for ${kitabPages[page].title}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Box(modifier = Modifier.fillMaxSize().background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f)),
                    startY = 500f
                )
            ))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                AnimatedSoundButton(
                    isExpanded = isButtonExpanded,
                    // --- PERBAIKAN DI SINI ---
                    onClick = { isButtonExpanded = !isButtonExpanded }
                )
            }

            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White else Color.White.copy(alpha = 0.5f)
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { pageIndex ->
            val pageData = kitabPages[pageIndex]
            KitabContent(title = pageData.title, content = pageData.content)
        }
    }
}

@Composable
fun KitabContent(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = content,
            textAlign = TextAlign.Justify,
            color = Color.Gray,
            lineHeight = 24.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun KitabScreenPreview() {
    KitabScreen()
}