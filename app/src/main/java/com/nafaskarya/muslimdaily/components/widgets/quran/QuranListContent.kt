// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/ui/quran/components/QuranListContent.kt

package com.nafaskarya.muslimdaily.components.widgets.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.ui.data.quran.Surah

@Composable
fun QuranListContent(surahs: List<Surah>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            LastReadCard()
            Spacer(modifier = Modifier.height(12.dp)) // Kurangi spacer
            SurahListHeader()
        }
        items(surahs) { surah ->
            SurahListItem(surah = surah)
        }
    }
}

@Composable
private fun LastReadCard() {
    Card(
        modifier = Modifier.fillMaxWidth().height(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Brush.horizontalGradient(listOf(Color(0xFFF9D7B8), Color(0xFFFBE6D3))))
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text("Last Read", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
                Column {
                    Text("Al-Fatihah", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("Ayah No: 1", fontSize = 14.sp, color = Color.DarkGray)
                }
            }
        }
    }
}

@Composable
private fun SurahListHeader() {
    var selectedTab by remember { mutableStateOf("Surah") }
    val tabs = listOf("Surah", "Juz", "Page")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Al Quran", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White).padding(4.dp)
        ) {
            tabs.forEach { tab ->
                val isSelected = selectedTab == tab
                TextButton(
                    onClick = { selectedTab = tab },
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

@Composable
private fun SurahListItem(surah: Surah) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFFBE6D3)),
            contentAlignment = Alignment.Center
        ) {
            Text(surah.number.toString(), fontWeight = FontWeight.SemiBold, color = Color.Black)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(surah.englishName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Book, "Ayahs", modifier = Modifier.size(14.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text("${surah.numberOfAyahs} Ayahs", color = Color.Gray, fontSize = 12.sp)
            }
        }
        Text(surah.arabicName, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF863ED5))
    }
}

@Preview(showBackground = true)
@Composable
private fun QuranListContentPreview() {
    val dummySurah = Surah(1, "Al-Fatihah", "ٱلْفَاتِحَة", "Pembukaan", 7, "Mekah")
    QuranListContent(surahs = List(5) { dummySurah })
}