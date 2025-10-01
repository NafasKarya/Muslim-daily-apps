package com.nafaskarya.muslimdaily.components.widgets.quran.surah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.ui.data.quran.surah.Ayah

@Composable
fun AyahListItem(
    surahNumber: Int,
    ayah: Ayah,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$surahNumber:${ayah.numberInSurah}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Pilihan Ayat", tint = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = ayah.arabicText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 24.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = ayah.translation,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.DarkGray,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconColor = Color.Gray
            IconButton(onClick = { /*TODO: Play audio from ayah.audio */ }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Putar Audio", tint = iconColor)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.BookmarkBorder, contentDescription = "Tandai Ayat", tint = iconColor)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Share, contentDescription = "Bagikan Ayat", tint = iconColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AyahListItemPreview() {
    val dummyAyah = Ayah(
        numberInSurah = 1,
        arabicText = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
        translation = "Dengan menyebut nama Allah Yang Maha Pemurah lagi Maha Penyayang.",
        audio = "url"
    )
    AyahListItem(surahNumber = 1, ayah = dummyAyah, modifier = Modifier.padding(16.dp))
}
