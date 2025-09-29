package com.nafaskarya.muslimdaily.ui.viewmodels // Sesuaikan dengan package Anda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ui.data.hijri.HijriDate
import ui.repository.hijri.HijriRepository
import ui.utils.state.UiState

/**
 * ViewModel untuk mengelola data dan state yang berhubungan dengan Kalender Hijriah.
 *
 * @param repository Sumber data untuk mengambil informasi Hijriah.
 */
class HijriViewModel(private val repository: HijriRepository) : ViewModel() {

    // _hijriDateState adalah MutableStateFlow internal yang hanya bisa diubah di dalam ViewModel ini.
    // Ini menyimpan state terkini (Loading, Success, atau Error).
    private val _hijriDateState = MutableStateFlow<UiState<HijriDate>>(UiState.Loading)

    // hijriDateState adalah StateFlow publik yang hanya bisa dibaca (read-only) oleh UI.
    // UI akan mengobservasi (collect) state dari sini.
    val hijriDateState: StateFlow<UiState<HijriDate>> = _hijriDateState.asStateFlow()

    // Blok init akan dieksekusi saat ViewModel pertama kali dibuat.
    // Kita langsung memanggil fungsi untuk mengambil data.
    init {
        fetchTodayHijriDate()
    }

    /**
     * Fungsi untuk mengambil data tanggal Hijriah hari ini dari repository.
     */
    fun fetchTodayHijriDate() {
        // viewModelScope adalah CoroutineScope yang terikat dengan lifecycle ViewModel.
        // Coroutine di dalamnya akan otomatis dibatalkan jika ViewModel dihancurkan.
        viewModelScope.launch {
            repository.getTodayHijriDate().collect { state ->
                // Setiap kali ada state baru dari repository (Loading, Success, Error),
                // kita perbarui nilai _hijriDateState.
                _hijriDateState.value = state
            }
        }
    }
}

/**
 * Factory untuk membuat instance dari HijriViewModel.
 * Diperlukan karena HijriViewModel memiliki parameter di constructor-nya (yaitu repository).
 */
class HijriViewModelFactory(private val repository: HijriRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HijriViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HijriViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
