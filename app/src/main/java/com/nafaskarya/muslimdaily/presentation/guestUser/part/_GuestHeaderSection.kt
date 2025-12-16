package com.nafaskarya.muslimdaily.presentation.guestUser.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

private val TextWhite = Color(0xFFEEEEEE)
private val TextGray = Color(0xFFAAAAAA)

@Composable
fun GuestHeaderSection(dimen: WindowDimensions) {
    val horizontalPadding = dimen.width * 0.05f
    val nameSize = dimen.getResponsiveTextSize(0.045f, min = 16f, max = 24f)
    val emailSize = dimen.getResponsiveTextSize(0.035f, min = 12f, max = 16f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = Color.LightGray
            ) {}

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Khalif Siregar",
                    fontSize = nameSize,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Si Paling Kece",
                    fontSize = emailSize,
                    color = TextGray
                )
            }
        }

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = TextWhite,
            modifier = Modifier
                .size(28.dp)
                .clickable { /* Handle notification click */ }
        )
    }
}
