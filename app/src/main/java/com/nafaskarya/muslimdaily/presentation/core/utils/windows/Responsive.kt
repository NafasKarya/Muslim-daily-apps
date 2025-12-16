package com.nafaskarya.muslimdaily.presentation.core.utils.windows

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Mengambil dimensi layar saat ini (Width & Height) menggunakan LocalConfiguration.
 * Berguna untuk membuat UI responsif tanpa perlu BoxWithConstraints di setiap tempat.
 */
@Composable
fun rememberWindowDimensions(): WindowDimensions {
    val configuration = LocalConfiguration.current
    return WindowDimensions(
        width = configuration.screenWidthDp.dp,
        height = configuration.screenHeightDp.dp
    )
}

data class WindowDimensions(
    val width: Dp,
    val height: Dp
) {
    /**
     * Menghitung ukuran teks responsif berdasarkan persentase lebar layar.
     * @param percentage Persentase lebar layar (0.0 - 1.0), misal 0.05 untuk 5%.
     * @param min Ukuran minimum sp.
     * @param max Ukuran maksimum sp.
     */
    fun getResponsiveTextSize(
        percentage: Float,
        min: Float = 12f,
        max: Float = 100f
    ): TextUnit {
        return (width.value * percentage).coerceIn(min, max).sp
    }

    /**
     * Menghitung tinggi spacer responsif berdasarkan persentase tinggi layar.
     */
    fun getResponsiveHeight(percentage: Float): Dp {
        return (height.value * percentage).dp
    }
}