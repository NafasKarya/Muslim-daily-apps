package com.nafaskarya.muslimdaily.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R // Ganti dengan R dari package Anda
import kotlinx.coroutines.delay
import kotlin.random.Random

// Warna Gradien yang Modern
val darkPurple = Color(0xFF2A004D)
val midPurple = Color(0xFF4B0082)
val lightBlue = Color(0xFF6AD7E5)

@Composable
fun NoInternetScreen(onRetry: () -> Unit) {

    // State untuk animasi saat layar pertama kali muncul
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100) // Sedikit delay agar animasi terlihat
        isVisible = true
    }

    // Transisi tanpa henti untuk animasi yang terus berjalan
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    // Animasi untuk karakter (naik-turun)
    val characterOffsetY by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "char_offset"
    )

    // Animasi untuk tombol (berdenyut)
    val buttonScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "btn_scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(darkPurple, midPurple)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Latar belakang dengan partikel animasi
        FloatingParticles()

        // Konten utama
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(
                initialOffsetY = { it / 2 },
                animationSpec = tween(1000, easing = EaseOutCubic)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Gambar Karakter
                Image(
                    painter = painterResource(id = R.drawable.img_mosque), // <-- GANTI DENGAN GAMBAR ANDA
                    contentDescription = "Karakter Offline",
                    modifier = Modifier
                        .size(220.dp)
                        .graphicsLayer {
                            translationY = characterOffsetY
                        },
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Teks Utama
                Text(
                    text = "Waduh, Sinyalnya Hilang!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Teks Deskripsi
                Text(
                    text = "Kayaknya kamu lagi nyasar di luar jangkauan. Yuk, coba cek koneksi atau Wi-Fi kamu.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Tombol Coba Lagi
                Button(
                    onClick = onRetry,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                        .scale(buttonScale),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue,
                        contentColor = darkPurple
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 4.dp
                    )
                ) {
                    Text(
                        text = "Coba Lagi Deh",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingParticles() {
    val transition = rememberInfiniteTransition(label = "particles")
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        val particleCount = 20
        repeat(particleCount) {
            // Properti acak untuk setiap partikel
            val startDelay = remember { Random.nextInt(0, 3000) }
            val duration = remember { Random.nextInt(1500, 4000) }
            val startX = remember { Random.nextFloat() }
            val startY = remember { Random.nextFloat() }
            val endY = remember { startY - (0.1f + Random.nextFloat() * 0.2f) } // Bergerak ke atas
            val size = remember { with(density) { Random.nextInt(4, 12).toDp() } }
            val initialAlpha = remember { 0.1f + Random.nextFloat() * 0.4f }

            val alpha by transition.animateFloat(
                initialValue = 0f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = duration
                        0f at 0 with LinearEasing
                        initialAlpha at (duration * 0.2).toInt() with LinearEasing
                        initialAlpha at (duration * 0.8).toInt() with LinearEasing
                        0f at duration with LinearEasing
                    },
                    repeatMode = RepeatMode.Restart,
                    initialStartOffset = StartOffset(startDelay, StartOffsetType.Delay)
                ), label = ""
            )

            val yPos by transition.animateFloat(
                initialValue = startY,
                targetValue = endY,
                animationSpec = infiniteRepeatable(
                    animation = tween(duration, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                    initialStartOffset = StartOffset(startDelay, StartOffsetType.Delay)
                ), label = ""
            )

            Particle(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
                    .offset(
                        x = (startX * with(density) { LocalContext.current.resources.displayMetrics.widthPixels.toDp().value }).dp,
                        y = (yPos * with(density) { LocalContext.current.resources.displayMetrics.heightPixels.toDp().value }).dp
                    ),
                size = size,
                alpha = alpha
            )
        }
    }
}


@Composable
private fun Particle(modifier: Modifier, size: Dp, alpha: Float) {
    Box(
        modifier = modifier
            .size(size)
            .alpha(alpha)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.5f))
    )
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun NoInternetScreenPreview() {
    MaterialTheme {
        NoInternetScreen(onRetry = {})
    }
}