package com.nafaskarya.muslimdaily.presentation.core.components.SliderCard

import androidx.compose.runtime.Composable
import com.nafaskarya.muslimdaily.presentation.core.shared.card.SharedSliderCardSection
import com.nafaskarya.muslimdaily.presentation.core.shared.card.ShortSong

@Composable
fun SliderCard() {
    // Mock Data versi kajian islami Gen Z
    val songList = listOf(
        ShortSong(
            title = "Tafsir Surat Al-Fatihah",
            artist = "Ust. Adi Hidayat",
            views = "120K listens",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/8/82/The_Animals_-_The_Animals.jpg"
        ),
        ShortSong(
            title = "Hijrah di Era Digital",
            artist = "Ust. Hanan Attaki",
            views = "230K listens",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/5/5e/Sam_Gellaitry_-_Assumptions.jpg"
        ),
        ShortSong(
            title = "Jaga Iman di Kampus",
            artist = "Ust. Abdul Somad",
            views = "98K listens",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/3/36/Somebody_That_I_Used_to_Know.png"
        ),
        ShortSong(
            title = "Ngaji Ringan Sebelum Tidur",
            artist = "Muslim Daily Originals",
            views = "75K listens",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/0/05/Laufey_-_From_the_Start.png"
        )
    )

    SharedSliderCardSection(
        title = "Kajian islami buat Gen Z",
        songs = songList,
        onPlayAllClick = {
            // TODO: handle "Play all"
        },
        onSongClick = { song ->
            // TODO: buka / play song
        },
        onSongMoreClick = { song ->
            // TODO: show more menu
        }
    )
}
