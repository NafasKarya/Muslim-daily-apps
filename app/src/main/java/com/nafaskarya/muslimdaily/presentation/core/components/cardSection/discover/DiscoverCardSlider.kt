package com.nafaskarya.muslimdaily.presentation.core.components.cardSection.discover

import androidx.compose.runtime.Composable
import com.nafaskarya.muslimdaily.R
import com.nafaskarya.muslimdaily.presentation.core.shared.card.DiscoverSong
import com.nafaskarya.muslimdaily.presentation.core.shared.card.SharedDiscoverCardSection

// ✅ OPTIMASI 5: Pindahkan data ke luar fungsi
private val discoverList = listOf(
    DiscoverSong(
        title = "Jaga Hati di Era Digital",
        artist = "Ust. Hanan Attaki",
        plays = "120K kali diputar",
        contextInfo = "Buat yang lagi ngerasa iman naik turun",
        imageModel = R.drawable.img_onboarding
    ),
    DiscoverSong(
        title = "Ngaji Santai Tapi Nancep",
        artist = "Ust. Adi Hidayat",
        plays = "250K kali diputar",
        contextInfo = "Pas banget nemenin begadang upgrade iman",
        imageModel = R.drawable.img_onboarding
    ),
    DiscoverSong(
        title = "Hijrah Pelan-Pelan Aja",
        artist = "Ust. Abdul Somad",
        plays = "300K kali diputar",
        contextInfo = "Buat kamu yang capek tapi masih pengen dekat Allah",
        imageModel = R.drawable.img_onboarding
    )
)

@Composable
fun DiscoverCardSlider() {
    SharedDiscoverCardSection(
        title = "Daily discover for your iman",
        songs = discoverList,
        onPlayAllClick = {
            // TODO: handle "Play all"
        },
        onCardClick = { song ->
            // TODO: buka / play detail kajian
        }
    )
}