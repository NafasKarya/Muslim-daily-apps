// Lokasi File: .../components/widgets/FindMosqueButton.kt
package com.nafaskarya.muslimdaily.components.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource // <-- IMPORT BARU
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember // <-- IMPORT BARU
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons

@Composable
fun FindMosqueButton(
    onClick: () -> Unit
) {
    // DIUBAH: Tambahkan remember { MutableInteractionSource() } untuk clickable
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            // DIUBAH: Gunakan clickable dengan indication = null untuk menghilangkan efek ripple
            .clickable(
                interactionSource = interactionSource,
                indication = null // Menghilangkan efek visual saat diklik
            ) {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // DIUBAH: Padding vertikal diperbesar dari 12.dp menjadi 18.dp
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.Mosque,
                contentDescription = "Mosque Icon",
                tint = Color(0xFF4FB7B3)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Find nearest mosque",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = AppIcons.ChevronRight,
                contentDescription = "Action Arrow",
                tint = Color.Gray
            )
        }
    }
}