// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/components/widgets/quran/SurahListHeader.kt

package com.nafaskarya.muslimdaily.components.widgets.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SurahListHeader(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("Surah", "Juz", "Page")
    Row(
        modifier = modifier
            .fillMaxWidth()
            // --- PERUBAHAN: Padding atas ditambah secara signifikan ---
            .padding(top = 48.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Al Quran", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(4.dp)
        ) {
            tabs.forEach { tab ->
                val isSelected = selectedTab == tab
                TextButton(
                    onClick = { onTabSelected(tab) },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (isSelected) Color(0xFFFBE6D3) else Color.Transparent,
                        contentColor = if (isSelected) Color.Black else Color.Gray
                    )
                ) {
                    Text(text = tab, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SurahListHeaderPreview() {
    var selectedTab by remember { mutableStateOf("Surah") }
    SurahListHeader(
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it },
        modifier = Modifier.padding(16.dp)
    )
}