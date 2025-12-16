package com.nafaskarya.muslimdaily.presentation.guestUser

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nafaskarya.muslimdaily.R

// Component Imports
import com.nafaskarya.muslimdaily.presentation.core.components.cardSection.CardSlider.MoreLikeThis
import com.nafaskarya.muslimdaily.presentation.core.components.cardSection.discover.DiscoverCardSlider
import com.nafaskarya.muslimdaily.presentation.core.components.SliderCard.SliderCard
import com.nafaskarya.muslimdaily.presentation.core.components.kitabSection.KitabSliderDailySection
import com.nafaskarya.muslimdaily.presentation.core.shared.footer.BottomNav
import com.nafaskarya.muslimdaily.presentation.core.shared.player.PlayerFooter
import com.nafaskarya.muslimdaily.presentation.core.shared.player.PlayerScreenBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.MoreMenuBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.ShareScreenBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.shared.player.timer.SleepTimerBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions
import com.nafaskarya.muslimdaily.presentation.guestUser.part.GuestCategorySection
import com.nafaskarya.muslimdaily.presentation.guestUser.part.GuestContentSection
import com.nafaskarya.muslimdaily.presentation.guestUser.part.GuestHeaderSection

private val BackgroundDark = Color(0xFF121212)

data class ContentItem(
    val title: String,
    val description: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuestScreen(navController: NavController) {
    val dimen = rememberWindowDimensions()

    // --- STATE MANAGEMENT ---
    var showPlayer by remember { mutableStateOf(false) }
    var showShare by remember { mutableStateOf(false) }
    var showMoreMenu by remember { mutableStateOf(false) }
    var showSleepTimer by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // --- SHADOW LOGIC ---
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    val scrimAlpha by remember {
        derivedStateOf {
            try {
                val currentOffset = sheetState.requireOffset()
                val progress = (currentOffset / screenHeightPx).coerceIn(0f, 1f)
                val fadeStartPoint = 0.3f
                val curve = if (progress < fadeStartPoint) {
                    1f
                } else {
                    1f - ((progress - fadeStartPoint) / (1f - fadeStartPoint))
                }
                (curve * 0.85f).coerceIn(0f, 1f)
            } catch (e: Exception) {
                if (showPlayer) 0.85f else 0f
            }
        }
    }

    // --- ANIMASI ---
    var startAnim by remember { mutableStateOf(false) }
    val screenAlpha by animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = ""
    )
    LaunchedEffect(Unit) { startAnim = true }

    // --- MOCK DATA ---
    val genZList = listOf(
        ContentItem("Galau Moment", "Kajian buat Gen Z yang lagi galau 亊ｫｶ", R.drawable.img_onboarding),
        ContentItem("Hijrah Kuy", "Hijrah yuk biar hidup makin barokah 嫌", R.drawable.img_onboarding),
        ContentItem("Pengen Tobat", "Kadang pengen mulai lagi dari nol, tanpa dosa yang sama.", R.drawable.img_onboarding),
        ContentItem("Gaul Vibes", "Santai boleh, tapi iman jangan libur 潮", R.drawable.img_onboarding)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .graphicsLayer { this.alpha = screenAlpha }
    ) {
        // 1. KONTEN GUEST
        Scaffold(containerColor = BackgroundDark) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 160.dp)
            ) {
                // ✅ Update Header untuk Navigasi
                item {
                    GuestHeaderSection(
                        dimen = dimen,
                        onProfileClick = {
                            navController.navigate("profile_screen")
                        }
                    )
                }

                item { GuestCategorySection(dimen) }
                item { GuestContentSection("Lagi Gen Z banget", genZList, dimen) { showPlayer = true } }

                // KITAB SLIDER (From Community)
                item { KitabSliderDailySection() }

                // HEARD IN SHORTS
                item { SliderCard() }

                // YOUR DAILY DISCOVER
                item { DiscoverCardSlider() }

                // 燥 TODAY'S BIGGEST HITS (DENGAN NAVIGASI)
                item {
                    MoreLikeThis(
                        onItemClick = { id ->
                            // Navigasi ke Playlist Screen
                            navController.navigate("playlist_screen")
                        }
                    )
                }
            }
        }

        // 2. FOOTER
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Box(modifier = Modifier.clickable { showPlayer = true }) {
                PlayerFooter()
            }
            BottomNav()
        }

        // 3. PLAYER (OVERLAY)
        if (showPlayer) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = scrimAlpha))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { showPlayer = false }
            )

            PlayerScreenBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showPlayer = false },
                onShareClick = { showShare = true },
                onMoreClick = { showMoreMenu = true },
                onTimerClick = { showSleepTimer = true }
            )
        }

        // 4. SHARE SCREEN
        if (showShare) {
            ShareScreenBottomSheet(
                onDismissRequest = { showShare = false },
                onBackClick = { showShare = false }
            )
        }

        // 5. MORE MENU
        if (showMoreMenu) {
            MoreMenuBottomSheet(
                onDismissRequest = { showMoreMenu = false }
            )
        }

        // 6. SLEEP TIMER
        if (showSleepTimer) {
            SleepTimerBottomSheet(
                onDismissRequest = { showSleepTimer = false },
                onTimerSelected = { selectedTime ->
                    println("Timer selected: $selectedTime")
                    showSleepTimer = false
                }
            )
        }
    }
}