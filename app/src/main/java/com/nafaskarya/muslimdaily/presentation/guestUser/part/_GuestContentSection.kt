package com.nafaskarya.muslimdaily.presentation.guestUser.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions
import com.nafaskarya.muslimdaily.presentation.guestUser.ContentItem

private val TextWhite = Color(0xFFEEEEEE)
private val TextGray = Color(0xFFAAAAAA)

@Composable
fun GuestContentSection(
    title: String,
    items: List<ContentItem>,
    dimen: WindowDimensions,
    onItemClick: (ContentItem) -> Unit // ✅ callback untuk klik item
) {
    val horizontalPadding = dimen.width * 0.05f
    val titleSize = dimen.getResponsiveTextSize(0.045f, min = 18f, max = 22f)

    Column {
        // 🔹 Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = titleSize,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            Text(
                text = "Lihat semuanya",
                fontSize = 12.sp,
                color = TextGray,
                modifier = Modifier.clickable { /* bisa diarahkan ke halaman kategori */ }
            )
        }

        // 🔹 Daftar konten horizontal
        LazyRow(
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                // ⬇️ kirim callback ketika diklik
                GuestContentCard(
                    item = item,
                    screenWidth = dimen.width,
                    onClick = { onItemClick(item) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
