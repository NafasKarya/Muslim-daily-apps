package com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.commons.CommonBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.constant.MoreMenuSeparatorColor
import com.nafaskarya.muslimdaily.presentation.core.constant.MoreMenuSheetBackground
import com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.part.MenuRowItem
import com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.part.MoreMenuHeader
import com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.part.TopActionsRow
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@Composable
fun MoreMenuScreen(
    onDismissRequest: () -> Unit
) {
    val dimen = rememberWindowDimensions()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MoreMenuSheetBackground)
            .navigationBarsPadding()
    ) {
        MoreMenuHeader(
            dimen = dimen,
            onClose = onDismissRequest
        )

        HorizontalDivider(thickness = 1.dp, color = MoreMenuSeparatorColor)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false)
                .verticalScroll(scrollState)
                .padding(bottom = 16.dp)
        ) {
            TopActionsRow(dimen)

            Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.01f)))

            MenuRowItem(dimen, Icons.Default.Radio, "Start mix")
            MenuRowItem(dimen, Icons.Default.QueueMusic, "Add to queue")
            MenuRowItem(dimen, Icons.Outlined.BookmarkBorder, "Save to library")
            MenuRowItem(dimen, Icons.Default.Download, "Download")
            MenuRowItem(dimen, Icons.Default.Album, "Go to album")
            MenuRowItem(dimen, Icons.Default.Person, "Go to artist")
            MenuRowItem(dimen, Icons.Default.Group, "View song credits")
            MenuRowItem(dimen, Icons.Default.PushPin, "Pin to Speed dial")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MoreMenuBottomSheet(
    onDismissRequest: () -> Unit
) {
    CommonBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = MoreMenuSheetBackground,
        skipPartiallyExpanded = true
    ) {
        MoreMenuScreen(
            onDismissRequest = onDismissRequest
        )
    }
}