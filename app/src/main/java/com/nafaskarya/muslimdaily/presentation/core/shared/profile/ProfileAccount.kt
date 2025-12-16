package com.nafaskarya.muslimdaily.presentation.core.shared.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileBackground
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileText
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.shared.profile.part.*
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAccountScreen(
    onBackClick: () -> Unit,
    onLogoutConfirmed: () -> Unit
) {
    val dimen = rememberWindowDimensions()
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Menggunakan Teks dari TextConstant
    val accountSettings = remember {
        listOf(
            MenuOption(ProfileText.MENU_PERSONAL_INFO, Icons.Default.Person),
            MenuOption(ProfileText.MENU_NOTIFICATIONS, Icons.Outlined.Notifications),
            MenuOption(ProfileText.MENU_PRIVACY, Icons.Outlined.Security),
            MenuOption(ProfileText.MENU_DATA_SAVER, Icons.Default.DataUsage)
        )
    }

    val appInfoSettings = remember {
        listOf(
            MenuOption(ProfileText.MENU_ABOUT, Icons.Default.Info),
            MenuOption(ProfileText.MENU_RATE, Icons.Default.Star)
        )
    }

    Scaffold(
        containerColor = ProfileBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = ProfileText.TITLE_PROFILE,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = dimen.getResponsiveTextSize(0.045f, max = 20f).value.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextWhite
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ProfileBackground
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimen.width * 0.05f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. HEADER PROFILE
            item(key = "header") {
                ProfileHeaderSection(dimen)
                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.03f)))
            }

            // 2. PREMIUM CARD
            item(key = "premium_card") {
                PremiumPlanCard(dimen)
                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.03f)))
            }

            // 3. ACCOUNT SETTINGS
            item(key = "title_account") {
                SettingsGroupTitle(ProfileText.GROUP_ACCOUNT)
            }
            items(
                items = accountSettings,
                key = { it.title }
            ) { item ->
                ProfileMenuItem(dimen, item.icon, item.title, item.action)
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // 4. APP INFO
            item(key = "title_app_info") {
                SettingsGroupTitle(ProfileText.GROUP_APP_INFO)
            }
            items(
                items = appInfoSettings,
                key = { it.title }
            ) { item ->
                ProfileMenuItem(dimen, item.icon, item.title, item.action)
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // 5. LOGOUT BUTTON
            item(key = "logout_btn") {
                LogoutButton(dimen) {
                    showLogoutDialog = true
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = ProfileText.APP_VERSION,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // --- POPUP LOGOUT ---
        if (showLogoutDialog) {
            LogoutConfirmationDialog(
                onDismiss = { showLogoutDialog = false },
                onConfirm = {
                    showLogoutDialog = false
                    onLogoutConfirmed()
                }
            )
        }
    }
}