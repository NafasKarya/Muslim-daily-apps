package com.nafaskarya.muslimdaily.presentation.auth.part

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.constant.BorderColor
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite

@Composable
fun OrDivider() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderColor)
        Text(
            text = "or",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = TextWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderColor)
    }
}
