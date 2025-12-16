package com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nafaskarya.muslimdaily.presentation.core.constant.ShareCardColors
import com.nafaskarya.muslimdaily.presentation.core.constant.ShareScreenBackground
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part.ShareColorPickerSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part.ShareLyricsCardSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part.ShareOptionsSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.part.ShareTopBar
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ShareOption(
    val label: String,
    val icon: ImageVector,
    val color: Color,
    val isGradient: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareScreen(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    val dimen = rememberWindowDimensions()
    var selectedColor by remember { mutableStateOf(ShareCardColors[0]) }

    Scaffold(
        containerColor = ShareScreenBackground,
        topBar = {
            ShareTopBar(
                dimen = dimen,
                onBackClick = onBackClick,
                onCloseClick = onCloseClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. LYRICS CARD
            ShareLyricsCardSection(
                dimen = dimen,
                selectedColor = selectedColor
            )

            // 2. COLOR PICKER
            ShareColorPickerSection(
                dimen = dimen,
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )

            // 3. SHARE OPTIONS
            ShareOptionsSection(dimen = dimen)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareScreenBottomSheet(
    onDismissRequest: () -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = ShareScreenBackground,
        dragHandle = null
    ) {
        ShareScreen(
            onBackClick = onBackClick,
            onCloseClick = onDismissRequest
        )
    }
}
