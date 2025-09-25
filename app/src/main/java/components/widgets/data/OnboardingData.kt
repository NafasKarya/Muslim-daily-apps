package com.nafaskarya.muslimdaily.onboarding.data

import com.nafaskarya.muslimdaily.R

/**
 * Model data untuk satu halaman onboarding.
 * Dibuat 'internal' agar hanya bisa diakses dari dalam modul yang sama.
 */
internal data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val subtitle: String
)

/**
 * Daftar semua halaman onboarding yang akan ditampilkan.
 */
internal val pages = listOf(
    // Halaman 1
    OnboardingPage(
        imageRes = R.drawable.img_fajr, // Ganti dengan gambar Jadwal Sholat
        title = "Sholat Tepat Waktu? Sat Set!",
        subtitle = "Jadwal sholat auto akurat, biar harimu makin berkah."
    ),
    // Halaman 2
    OnboardingPage(
        imageRes = R.drawable.img_tahajud, // Ganti dengan gambar Al-Qur'an
        title = "Butuh Healing? Baca Qur'an Aja.",
        subtitle = "Al-Qur'an digital lengkap di genggaman, kapan aja, di mana aja."
    ),
    // Halaman 3
    OnboardingPage(
        imageRes = R.drawable.img_dhuha, // Ganti dengan gambar Kompas Kiblat
        title = "Nyari Kiblat Gak Pake Ribet.",
        subtitle = "Lagi di mana pun, arah kiblat auto ketemu. Tinggal klik!"
    ),
    // Halaman 4
    OnboardingPage(
        imageRes = R.drawable.img_fajr, // Ganti dengan gambar Konten Harian
        title = "Asupan Iman Biar Gak Lowbatt.",
        subtitle = "Dapetin kutipan hadis dan doa harian yang relate sama kamu."
    ),
    // Halaman 5
    OnboardingPage(
        imageRes = R.drawable.img_tahajud, // Ganti dengan gambar Tasbih Digital
        title = "Dzikir Digital, Makin Chill.",
        subtitle = "Hitung dzikir-mu pake tasbih digital. Simpel dan gak akan miss."
    ),
    // Halaman 6
    OnboardingPage(
        imageRes = R.drawable.img_dhuha, // Ganti dengan gambar Ilustrasi Hijrah
        title = "Siap Jadi The Best Version of You?",
        subtitle = "Yuk, mulai perjalanan hijrah-mu bareng Muslim Daily!"
    )
)