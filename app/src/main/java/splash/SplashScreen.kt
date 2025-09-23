package com.nafaskarya.muslimdaily

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val alpha = remember { Animatable(0f) }

    // Efek animasi fade in, delay, dan fade out
    LaunchedEffect(Unit) {
        alpha.animateTo(1f, tween(1000)) // fade in 1 detik
        delay(2000)                      // tahan 2 detik
        alpha.animateTo(0f, tween(500))  // fade out 0.5 detik
        onTimeout()                      // Panggil aksi setelah selesai
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .alpha(alpha.value), // Terapkan animasi alpha (transparansi)
        contentAlignment = Alignment.Center
    ) {
        // ✅ Logo di tengah
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_muslim_daily),
                contentDescription = "App Logo",
                modifier = Modifier
                    .height(screenHeight * 0.35f)
                    .fillMaxWidth()
            )
        }

        // ✅ Teks "from NafasKarya" di bawah
        Text(
            text = "from NafasKarya",
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}