// File: com/nafaskarya/muslimdaily/components/shared/refresh/RefreshableContent.kt

package com.nafaskarya.muslimdaily.components.shared.refresh // <-- PERUBAHAN DI SINI

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.layouts.theme.AppIcons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshableContent(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            onRefresh()
        }
    }

    LaunchedEffect(isRefreshing) {
        if (!isRefreshing) {
            pullToRefreshState.endRefresh()
        }
    }

    Box(
        modifier = modifier
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        content()
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            indicator = { state ->
                GifPullToRefreshIndicator(state = state)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GifPullToRefreshIndicator(
    state: PullToRefreshState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.verticalOffset > 0f || state.isRefreshing) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (state.isRefreshing) {
                    AsyncImage(
                        model = R.drawable.loading_animation,
                        contentDescription = "Loading animation",
                        imageLoader = imageLoader,
                        modifier = Modifier.size(140.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = AppIcons.Mosque.inactive),
                        contentDescription = "Pull to refresh",
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF4FB7B3)
                    )
                }
            }
        }
    }
}