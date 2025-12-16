package com.nafaskarya.muslimdaily.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {
    val deepBaseColor = Color(0xFF050B14)
    val hazeColor = Color(0xFF1F4058)

    val dimen = rememberWindowDimensions()
    val responsiveTitleSize = dimen.getResponsiveTextSize(0.08f, min = 24f, max = 80f)
    val responsiveSubtitleSize = dimen.getResponsiveTextSize(0.035f, min = 12f, max = 32f)
    val responsiveSpacerHeight = dimen.getResponsiveHeight(0.015f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(deepBaseColor),
        contentAlignment = Alignment.Center
    ) {
        TurbulentWaterEffect(hazeColor = hazeColor)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var startAnimation by remember { mutableStateOf(false) }
            val interpolator = remember { OvershootInterpolator(1.5f) }

            val alphaAnim by animateFloatAsState(
                targetValue = if (startAnimation) 1f else 0f,
                animationSpec = tween(durationMillis = 1500),
                label = ""
            )
            val scaleAnim by animateFloatAsState(
                targetValue = if (startAnimation) 1f else 0.9f,
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = { interpolator.getInterpolation(it) }
                ),
                label = ""
            )

            LaunchedEffect(Unit) {
                startAnimation = true
                delay(3000)
                onSplashFinished()
            }

            Text(
                text = "Muslim Daily",
                color = Color.White.copy(alpha = 0.95f),
                fontSize = responsiveTitleSize * scaleAnim,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                modifier = Modifier.graphicsLayer {
                    alpha = alphaAnim
                    scaleX = scaleAnim
                    scaleY = scaleAnim
                }
            )

            Spacer(modifier = Modifier.height(responsiveSpacerHeight))

            Text(
                text = "Experience",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = responsiveSubtitleSize,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                modifier = Modifier.graphicsLayer {
                    alpha = alphaAnim
                }
            )
        }
    }
}

@Composable
fun TurbulentWaterEffect(hazeColor: Color) {
    val transition = rememberInfiniteTransition(label = "")

    val drift1 by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(18000, easing = LinearEasing), RepeatMode.Reverse),
        label = ""
    )

    val drift2 by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(25000, easing = LinearEasing), RepeatMode.Reverse),
        label = ""
    )

    val pulse by transition.animateFloat(
        initialValue = 0.8f, targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(10000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val transparent = Color.Transparent

        val cx1 = w * 0.5f + (sin(drift1 * Math.PI) * w * 0.2f).toFloat()
        val cy1 = h * 0.9f
        val rx1 = w * 1.2f
        val ry1 = h * 0.6f

        drawOval(
            brush = Brush.radialGradient(
                colors = listOf(hazeColor.copy(alpha = 0.15f), hazeColor.copy(alpha = 0.075f), transparent),
                center = Offset(cx1, cy1),
                radius = maxOf(rx1, ry1)
            ),
            topLeft = Offset(cx1 - rx1, cy1 - ry1),
            size = Size(rx1 * 2, ry1 * 2)
        )

        val flowX = w * 0.8f - (drift2 * w * 0.4f)
        val flowY = h * 0.7f + (sin(drift2 * Math.PI) * h * 0.1f).toFloat()
        val rx2 = w * 0.8f * pulse
        val ry2 = h * 0.4f

        drawOval(
            brush = Brush.radialGradient(
                colors = listOf(hazeColor.copy(alpha = 0.18f), hazeColor.copy(alpha = 0.09f), transparent),
                center = Offset(flowX, flowY),
                radius = maxOf(rx2, ry2)
            ),
            topLeft = Offset(flowX - rx2, flowY - ry2),
            size = Size(rx2 * 2, ry2 * 2)
        )

        val highlightX = w * 0.7f + (cos(drift1 * 2) * w * 0.1f).toFloat()
        val highlightY = h * 0.85f + (sin(drift1 * 2) * h * 0.05f).toFloat()
        val rx3 = w * 0.6f
        val ry3 = w * 0.3f

        drawOval(
            brush = Brush.radialGradient(
                colors = listOf(hazeColor.copy(alpha = 0.12f), hazeColor.copy(alpha = 0.06f), transparent),
                center = Offset(highlightX, highlightY),
                radius = maxOf(rx3, ry3)
            ),
            topLeft = Offset(highlightX - rx3, highlightY - ry3),
            size = Size(rx3 * 2, ry3 * 2)
        )
    }
}

@Preview(name = "Phone", device = Devices.PHONE, showBackground = true)
@Preview(name = "Tablet", device = Devices.TABLET, showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}