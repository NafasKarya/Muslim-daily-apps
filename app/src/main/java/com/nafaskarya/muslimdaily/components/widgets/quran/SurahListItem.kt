// Lokasi: app/src/main/java/com/nafaskarya/muslimdaily/components/widgets/quran/SurahListItem.kt

package com.nafaskarya.muslimdaily.components.widgets.quran

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.ui.data.quran.Surah

@Composable
fun SurahListItem(
    surah: Surah,
    modifier: Modifier = Modifier,
    onSurahClick: (Surah) -> Unit // --- 1. Tambahkan parameter lambda untuk klik ---
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSurahClick(surah) } // --- 2. Tambahkan modifier clickable ---
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_quran_border),
                contentDescription = "Surah number border",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = surah.number.toString(),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(24.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(surah.englishName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Book, "Ayahs", modifier = Modifier.size(14.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text("${surah.numberOfAyahs} Ayahs", color = Color.Gray, fontSize = 12.sp)
            }
        }
        Text(
            text = surah.arabicName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SurahListItemPreview() {
    val dummySurah = Surah(114, "An-Nas", "النَّاسِ", "Manusia", 6, "Mekah")
    // --- 3. Perbarui preview dengan lambda kosong ---
    SurahListItem(
        surah = dummySurah,
        modifier = Modifier.padding(horizontal = 16.dp),
        onSurahClick = {}
    )
}