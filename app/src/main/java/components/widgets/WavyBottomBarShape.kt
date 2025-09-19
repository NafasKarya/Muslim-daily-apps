package com.nafaskarya.muslimdaily.components.widgets // (Sesuaikan dengan package Anda)

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

// Shape kustom untuk membuat lekukan ombak yang halus
class WavyBottomBarShape(
    private val fabDiameter: Dp,
    private val swoopDepth: Dp,
    private val cornerRadius: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val fabDiameterPx = with(density) { fabDiameter.toPx() }
            val swoopDepthPx = with(density) { swoopDepth.toPx() }
            val cornerRadiusPx = with(density) { cornerRadius.toPx() }

            val centerX = size.width / 2
            val fabRadius = fabDiameterPx / 2

            // Jarak dari tengah ke titik awal/akhir lekukan
            val cutoutHalfWidth = fabRadius + cornerRadiusPx

            // Titik awal dan akhir lekukan ombak
            val cutoutStart = centerX - cutoutHalfWidth
            val cutoutEnd = centerX + cutoutHalfWidth

            // Mulai dari pojok kiri atas
            moveTo(0f, cornerRadiusPx)
            // Sudut tumpul kiri atas
            quadraticBezierTo(0f, 0f, cornerRadiusPx, 0f)

            // Garis lurus ke awal lekukan
            lineTo(cutoutStart, 0f)

            // Kurva kubik pertama (sisi kiri lekukan)
            cubicTo(
                x1 = cutoutStart + cornerRadiusPx + (cornerRadiusPx / 2),
                y1 = 0f,
                x2 = centerX - fabRadius,
                y2 = swoopDepthPx,
                x3 = centerX,
                y3 = swoopDepthPx
            )

            // Kurva kubik kedua (sisi kanan lekukan) yang sudah diperbaiki
            cubicTo(
                x1 = centerX + fabRadius,
                y1 = swoopDepthPx,
                x2 = cutoutEnd - cornerRadiusPx - (cornerRadiusPx / 2),
                y2 = 0f, // <-- Diperbaiki dari y3
                x3 = cutoutEnd,
                y3 = 0f  // <-- Diperbaiki dari y4
            )

            // Garis lurus ke akhir
            lineTo(size.width - cornerRadiusPx, 0f)
            // Sudut tumpul kanan atas
            quadraticBezierTo(size.width, 0f, size.width, cornerRadiusPx)

            // Selesaikan bentuk persegi panjang
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}