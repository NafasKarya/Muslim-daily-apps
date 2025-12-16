package com.nafaskarya.muslimdaily.presentation.core.shared.profile.part

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileSurface
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileText
import com.nafaskarya.muslimdaily.presentation.core.constant.TextGray
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun ProfileHeaderSection(dimen: WindowDimensions) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ProfileText.DUMMY_AVATAR_URL)
                .crossfade(true)
                .build(),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(dimen.width * 0.25f)
                .clip(CircleShape)
                .border(2.dp, Color.White.copy(alpha = 0.1f), CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = ProfileText.DUMMY_NAME,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = dimen.getResponsiveTextSize(0.06f, min = 20f, max = 24f)
            )
        )

        Text(
            text = ProfileText.DUMMY_EMAIL,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextGray,
                fontSize = dimen.getResponsiveTextSize(0.035f, min = 12f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(ProfileSurface)
                .clickable { }
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = ProfileText.EDIT_PROFILE,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}