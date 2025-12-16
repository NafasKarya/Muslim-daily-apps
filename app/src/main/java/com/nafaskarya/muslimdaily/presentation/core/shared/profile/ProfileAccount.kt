package com.nafaskarya.muslimdaily.presentation.core.shared.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.rememberWindowDimensions

// --- CONSTANTS ---
private val BackgroundColor = Color(0xFF121212)
private val SurfaceColor = Color(0xFF1F1F1F)
private val DividerColor = Color(0xFF2C2C2C)
private val RedDanger = Color(0xFFEF5350)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAccountScreen(
    onBackClick: () -> Unit,
    onLogoutConfirmed: () -> Unit
) {
    val dimen = rememberWindowDimensions()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
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
                    containerColor = BackgroundColor
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
            item {
                ProfileHeaderSection(dimen)
                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.03f)))
            }

            // 2. PREMIUM CARD (Optional aesthetic)
            item {
                PremiumPlanCard(dimen)
                Spacer(modifier = Modifier.height(dimen.getResponsiveHeight(0.03f)))
            }

            // 3. MENU ITEMS
            item {
                SettingsGroupTitle("Account Settings")
                ProfileMenuItem(dimen, Icons.Default.Person, "Personal Information") {}
                ProfileMenuItem(dimen, Icons.Outlined.Notifications, "Notifications") {}
                ProfileMenuItem(dimen, Icons.Outlined.Security, "Privacy & Social") {}
                ProfileMenuItem(dimen, Icons.Default.DataUsage, "Data Saver") {}

                Spacer(modifier = Modifier.height(24.dp))

                SettingsGroupTitle("App Info")
                ProfileMenuItem(dimen, Icons.Default.Info, "About Muslim Daily") {}
                ProfileMenuItem(dimen, Icons.Default.Star, "Rate Us") {}

                Spacer(modifier = Modifier.height(24.dp))
            }

            // 4. LOGOUT BUTTON
            item {
                LogoutButton(dimen) {
                    showLogoutDialog = true
                }
                // Version Info
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Version 1.0.0",
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

// --- COMPONENTS ---

@Composable
fun ProfileHeaderSection(dimen: WindowDimensions) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        AsyncImage(
            model = "https://upload.wikimedia.org/wikipedia/en/0/0e/Giyu_Tomioka.jpg", // Ganti URL User
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(dimen.width * 0.25f) // ~100dp
                .clip(CircleShape)
                .border(2.dp, Color.White.copy(alpha = 0.1f), CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        Text(
            text = "Khalif Siregar",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = dimen.getResponsiveTextSize(0.06f, min = 20f, max = 24f)
            )
        )

        // Email
        Text(
            text = "khalif@muslimdaily.com",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontSize = dimen.getResponsiveTextSize(0.035f, min = 12f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Profile Button (Small Pill)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(SurfaceColor)
                .clickable { /* TODO: Navigate to Edit Profile */ }
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = TextWhite,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun PremiumPlanCard(dimen: WindowDimensions) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Muslim Daily Premium",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Active until Dec 2025",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF1DB954) // Green
                    )
                )
            }
            Icon(
                imageVector = Icons.Default.Diamond,
                contentDescription = null,
                tint = Color(0xFF1DB954)
            )
        }
    }
}

@Composable
fun SettingsGroupTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium.copy(
            color = TextWhite.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}

@Composable
fun ProfileMenuItem(
    dimen: WindowDimensions,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextWhite,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TextWhite,
                fontSize = dimen.getResponsiveTextSize(0.04f, min = 14f)
            ),
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
fun LogoutButton(
    dimen: WindowDimensions,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = SurfaceColor,
            contentColor = RedDanger
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentPadding = PaddingValues(0.dp) // Reset default padding
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Log Out",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

// --- DIALOG ---
@Composable
fun LogoutConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = SurfaceColor,
        title = {
            Text(
                text = "Log Out",
                color = TextWhite,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = "Are you sure you want to log out from Muslim Daily?",
                color = TextWhite.copy(alpha = 0.8f)
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = RedDanger)
            ) {
                Text("Log Out", color = TextWhite, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextWhite, fontWeight = FontWeight.Bold)
            }
        }
    )
}