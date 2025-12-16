package com.nafaskarya.muslimdaily.presentation.guestUser.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.guestUser.ContentItem

private val SurfaceDark = Color(0xFF2C2C2C)
private val TextWhite = Color(0xFFEEEEEE)
private val TextGray = Color(0xFFAAAAAA)

@Composable
fun GuestContentCard(
    item: ContentItem,
    screenWidth: Dp,
    onClick: (ContentItem) -> Unit // ✅ tambahkan callback klik
) {
    val cardWidth = screenWidth * 0.4f

    Column(
        modifier = Modifier
            .width(cardWidth)
            .clickable { onClick(item) } // ✅ panggil callback saat diklik
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.title,
            color = TextWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.description,
            color = TextGray,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
