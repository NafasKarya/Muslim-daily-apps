package com.nafaskarya.muslimdaily.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nafaskarya.muslimdaily.ui.data.quran.surah.SurahDetail
import com.nafaskarya.muslimdaily.ui.repository.quran.surah.SurahAlQuranRepository
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Manages UI state for the Surah detail screen.
 * Disederhanakan untuk menangani satu panggilan API yang efisien.
 */
class SurahAlQuranViewModel(private val repository: SurahAlQuranRepository) : ViewModel() {

    // HANYA BUTUH SATU STATE untuk menampung semua data (Surah dan daftar Ayah)
    private val _surahDetailState = MutableStateFlow<UiState<SurahDetail>>(UiState.Loading)
    val surahDetailState: StateFlow<UiState<SurahDetail>> = _surahDetailState.asStateFlow()

    /**
     * Mengambil detail lengkap surah, termasuk semua ayatnya, dalam satu panggilan.
     * @param surahNumber Nomor surah yang akan diambil.
     */
    fun fetchSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            repository.getSurahDetail(surahNumber).collect { state ->
                _surahDetailState.value = state
            }
        }
    }
}


/**
 * Factory untuk membuat instance [SurahAlQuranViewModel].
 */
@Suppress("UNCHECKED_CAST")
class SurahAlQuranViewModelFactory(private val repository: SurahAlQuranRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurahAlQuranViewModel::class.java)) {
            return SurahAlQuranViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

