package com.nafaskarya.muslimdaily.presentation.core.routes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Routes
import com.nafaskarya.muslimdaily.presentation.auth.LoginScreen
import com.nafaskarya.muslimdaily.presentation.guestUser.GuestScreen
import com.nafaskarya.muslimdaily.presentation.onboarding.OnboardingScreen
import com.nafaskarya.muslimdaily.presentation.splash.SplashScreen
import com.nafaskarya.muslimdaily.presentation.core.shared.player.sharing.ShareScreen
import com.nafaskarya.muslimdaily.presentation.core.shared.player.moreMenu.MoreMenuBottomSheet
import com.nafaskarya.muslimdaily.presentation.core.shared.playlist.PlaylistScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoutes() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        NavHost(
            navController = navController,
            startDestination = AppDestination.Splash.route
        ) {
            // SPLASH
            composable(AppDestination.Splash.route) {
                SplashScreen {
                    navController.navigate(AppDestination.Guest.route) {
                        popUpTo(AppDestination.Splash.route) { inclusive = true }
                    }
                }
            }

            // GUEST (HOME)
            composable(AppDestination.Guest.route) {
                GuestScreen(navController)
            }

            // 👇 ROUTE BARU: PLAYLIST SCREEN
            composable("playlist_screen") {
                PlaylistScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // LOGIN
            composable(AppDestination.Login.route) {
                LoginScreen(
                    onLoginClick = { /* TODO */ },
                    onSignUpClick = { navController.navigate(AppDestination.Signup.route) }
                )
            }

            // ONBOARDING
            composable(AppDestination.Onboarding.route) {
                OnboardingScreen(
                    onLoginClick = { navController.navigate(AppDestination.Login.route) },
                    onSignUpClick = { navController.navigate(AppDestination.Signup.route) }
                )
            }

            // SIGNUP
            composable(AppDestination.Signup.route) {
                // SignupScreen()
            }

            // Route Cadangan (Share & Menu)
            composable("share") {
                ShareScreen(
                    onBackClick = { navController.popBackStack() },
                    onCloseClick = { navController.popBackStack() }
                )
            }
            composable("player_more_menu") {
                MoreMenuBottomSheet(
                    onDismissRequest = { navController.popBackStack() }
                )
            }
        }
    }
}