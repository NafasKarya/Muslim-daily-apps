package com.nafaskarya.muslimdaily

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nafaskarya.muslimdaily.components.shared.kitab.KitabScreen // <-- DITAMBAHKAN: Import untuk detail kitab
import com.nafaskarya.muslimdaily.components.shared.notifications.SettingsScreen
import com.nafaskarya.muslimdaily.components.shared.quran.QuranScreen
import com.nafaskarya.muslimdaily.guest.GuestDashboard
import com.nafaskarya.muslimdaily.components.shared.quran.surah.SurahScreen
import com.nafaskarya.muslimdaily.ui.kitab.KitabMenuScreen
import com.nafaskarya.muslimdaily.ui.repository.notification.SettingsRepository
import com.nafaskarya.muslimdaily.ui.repository.quran.surah.SurahAlQuranRepository
import com.nafaskarya.muslimdaily.ui.utils.network.RetrofitClient
import com.nafaskarya.muslimdaily.ui.viewmodel.SurahAlQuranViewModelFactory
import com.nafaskarya.muslimdaily.viewmodel.SettingsViewModel
import com.nafaskarya.muslimdaily.viewmodel.SettingsViewModelFactory


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "dashboard_route"
                    ) {
                        // ... (composable lainnya tidak berubah)
                        composable("dashboard_route") {
                            GuestDashboard(navController = navController)
                        }

                        composable("settings_route") {
                            val settingsRepository = remember { SettingsRepository(applicationContext) }
                            val factory = remember(settingsRepository) {
                                SettingsViewModelFactory(settingsRepository, applicationContext)
                            }
                            val settingsViewModel: SettingsViewModel = viewModel(factory = factory)

                            SettingsScreen(
                                viewModel = settingsViewModel,
                                onBackClick = { navController.navigateUp() }
                            )
                        }

                        composable("quran_route") {
                            QuranScreen(navController = navController)
                        }

                        composable(
                            route = "surah_detail/{surahNumber}",
                            arguments = listOf(navArgument("surahNumber") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val surahNumber = backStackEntry.arguments!!.getInt("surahNumber")
                            val apiService = RetrofitClient.surahAlQuranApiService
                            val factory = SurahAlQuranViewModelFactory(
                                SurahAlQuranRepository(apiService)
                            )
                            SurahScreen(
                                surahNumber = surahNumber,
                                viewModel = viewModel(factory = factory),
                                onBackClick = { navController.navigateUp() }
                            )
                        }

                        // DIUBAH: Sediakan parameter onNavigateToDetail
                        composable("kitab_route") {
                            KitabMenuScreen(
                                onNavigateToDetail = {
                                    navController.navigate("kitab_detail_route")
                                }
                            )
                        }

                        // DITAMBAHKAN: Rute baru untuk halaman detail kitab
                        composable("kitab_detail_route") {
                            KitabScreen()
                        }

                        composable("qibla_route") {
                            PlaceholderScreen(text = "Halaman Arah Qiblat")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}