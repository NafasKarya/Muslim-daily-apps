package com.nafaskarya.muslimdaily.presentation.core.routes

sealed class AppDestination(val route: String) {
    data object Splash : AppDestination("splash")
    data object Guest : AppDestination("guest")
    data object Onboarding : AppDestination("onboarding")
    data object Login : AppDestination("login")
    data object Signup : AppDestination("signup")
    data object ForgotPassword : AppDestination("forgot_password")
    data object Home : AppDestination("home")

    // 🚀 Player screen baru (untuk card klik di Guest)
    data object Player : AppDestination("player")
}
