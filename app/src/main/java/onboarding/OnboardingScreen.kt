package com.nafaskarya.muslimdaily.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.onboarding.components.OnboardingBottomSection
import com.nafaskarya.muslimdaily.onboarding.components.OnboardingPageContent
import com.nafaskarya.muslimdaily.onboarding.data.pages
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Lapisan 1: Konten Pager sebagai latar belakang utama
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { index -> pages[index].title },
            userScrollEnabled = false
        ) { pageIndex ->
            OnboardingPageContent(page = pages[pageIndex])
        }

        // Lapisan 2: Lapisan transparan khusus untuk mendeteksi gestur tahan/lepas.
        // Lapisan ini ada di atas Pager, tapi di bawah tombol.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        }
                    )
                }
        )

        // Lapisan 3: Tombol dan kontrol UI berada di lapisan paling atas,
        // sehingga sentuhan akan diterima oleh mereka terlebih dahulu.
        TextButton(
            onClick = onFinish,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(text = "Skip", color = Color.White, fontSize = 16.sp)
        }

        OnboardingBottomSection(
            pagerState = pagerState,
            onNextClicked = {
                scope.launch {
                    if (pagerState.currentPage < pages.size - 1) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        onFinish()
                    }
                }
            },
            isPressed = isPressed,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        )
    }
}