package com.nafaskarya.muslimdaily.ui.utils.state // Sesuaikan dengan package Anda

/**
 * Sealed interface untuk merepresentasikan state UI.
 * Menggunakan generics <T> agar bisa dipakai untuk tipe data apa pun.
 */
sealed interface UiState<out T> {
    /**
     * State ketika data sedang dimuat.
     */
    data object Loading : UiState<Nothing>

    /**
     * State ketika data berhasil didapatkan.
     * @param data Data yang berhasil diambil.
     */
    data class Success<T>(val data: T) : UiState<T>

    /**
     * State ketika terjadi error saat memuat data.
     * @param message Pesan error yang terjadi.
     */
    data class Error(val message: String) : UiState<Nothing>
}
