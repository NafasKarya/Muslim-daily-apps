package com.nafaskarya.muslimdaily.presentation.core.shared.player.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.commons.CommonBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

// --- CONSTANTS ---
private val SheetBackground = Color(0xFF1F1F1F) // Warna Background Gelap
private val HandleColor = Color(0xFF505050)    // Warna Garis Handle

// Data Opsi Timer
private val TimerOptions = listOf(
    "5 minutes",
    "10 minutes",
    "15 minutes",
    "30 minutes",
    "45 minutes",
    "1 hour",
    "End of track"
)

// --- CONTENT SCREEN ---
@Composable
fun SleepTimerScreen(
    onOptionSelected: (String) -> Unit
) {
    val dimen = rememberWindowDimensions()

    Scaffold(
        // 👇 MODIFIKASI DISINI:
        // Batasi tinggi maksimal hanya 55% dari layar (mirip screenshot)
        modifier = Modifier.fillMaxHeight(0.55f),

        containerColor = SheetBackground,
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Visual Drag Handle (Garis Kecil di atas)
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .width(36.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(50))
                        .background(HandleColor)
                )

                // 2. Judul "Sleep timer"
                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.02f)))
                Text(
                    text = "Sleep timer",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimen.getResponsiveTextSize(0.045f, min = 16f, max = 20f)
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.02f)))
            }
        }
    ) { paddingValues ->
        // 3. List Opsi Waktu
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = dimen.getResponsiveHeight(0.05f))
        ) {
            items(TimerOptions) { option ->
                TimerOptionItem(
                    label = option,
                    onClick = { onOptionSelected(option) },
                    fontSize = dimen.getResponsiveTextSize(0.04f, min = 14f),
                    verticalPadding = dimen.getResponsiveHeight(0.02f),
                    horizontalPadding = dimen.width * 0.06f
                )
            }
        }
    }
}

@Composable
fun TimerOptionItem(
    label: String,
    onClick: () -> Unit,
    fontSize: androidx.compose.ui.unit.TextUnit,
    verticalPadding: androidx.compose.ui.unit.Dp,
    horizontalPadding: androidx.compose.ui.unit.Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TextWhite,
                fontWeight = FontWeight.Medium,
                fontSize = fontSize
            )
        )
    }
}

// --- BOTTOM SHEET WRAPPER ---
@Composable
fun SleepTimerBottomSheet(
    onDismissRequest: () -> Unit,
    onTimerSelected: (String) -> Unit
) {
    CommonBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = SheetBackground,
        skipPartiallyExpanded = false
    ) {
        SleepTimerScreen(
            onOptionSelected = { selectedTime ->
                onTimerSelected(selectedTime)
                onDismissRequest()
            }
        )
    }
}