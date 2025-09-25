package com.nafaskarya.muslimdaily.components.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons

@Composable
fun FindMosqueButton(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            // Mengurangi radius sudut border agar konsisten
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        // Mengurangi radius sudut Card
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // 1. Padding vertikal dikurangi secara signifikan
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = AppIcons.Mosque.inactive),
                contentDescription = "Mosque Icon",
                tint = Color(0xFF4FB7B3),
                // 2. Ukuran ikon diperkecil ke ukuran standar
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Find nearest mosque",
                fontWeight = FontWeight.Medium,
                // 3. Ukuran font diperkecil (opsional, tapi disarankan)
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = AppIcons.ChevronRight),
                contentDescription = "Action Arrow",
                tint = Color.Gray,
                // 4. Ukuran ikon chevron juga diperkecil
                modifier = Modifier.size(24.dp)
            )
        }
    }
}