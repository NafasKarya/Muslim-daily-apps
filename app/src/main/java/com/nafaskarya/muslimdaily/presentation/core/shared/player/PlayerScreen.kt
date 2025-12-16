package com.nafaskarya.muslimdaily.presentation.core.shared.player

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nafaskarya.muslimdaily.presentation.core.constant.PlayerScreenBackground
import com.nafaskarya.muslimdaily.presentation.core.shared.player.part.PlayerArtworkSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.part.PlayerControlsSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.part.PlayerFooterActionsSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.part.PlayerHeaderSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.part.PlayerInfoSection
import com.nafaskarya.muslimdaily.presentation.core.shared.player.part.PlayerSeekBarSection
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreenBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onShareClick: () -> Unit,
    onMoreClick: () -> Unit,
    onTimerClick: () -> Unit, // 👈 1. Terima Callback Timer
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = PlayerScreenBackground,
        scrimColor = Color.Transparent,
        dragHandle = null,
        modifier = modifier
    ) {
        PlayerContent(
            onCollapse = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onDismissRequest()
                    }
                }
            },
            onShareClick = onShareClick,
            onMoreClick = onMoreClick,
            onTimerClick = onTimerClick // 👈 2. Teruskan ke Content
        )
    }
}

@Composable
fun PlayerContent(
    onCollapse: () -> Unit,
    onShareClick: () -> Unit,
    onMoreClick: () -> Unit,
    onTimerClick: () -> Unit // 👈 3. Terima di Content
) {
    val dimen = rememberWindowDimensions()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen.width * 0.06f,
                vertical = dimen.getResponsiveHeight(0.02f)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerHeaderSection(
            modifier = Modifier.fillMaxWidth(),
            onCollapse = onCollapse,
            onMoreClick = onMoreClick
        )

        Spacer(modifier = Modifier.weight(1f))

        PlayerArtworkSection(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(dimen.width * 0.02f)
        )

        Spacer(modifier = Modifier.weight(1f))

        PlayerInfoSection(
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.03f)))

        PlayerSeekBarSection()

        Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.02f)))

        // 👇 4. Pasang Callback di PlayerControlsSection
        PlayerControlsSection(
            onTimerClick = onTimerClick
        )

        Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.04f)))

        PlayerFooterActionsSection(
            onShareClick = onShareClick
        )

        Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.02f)))
    }
}