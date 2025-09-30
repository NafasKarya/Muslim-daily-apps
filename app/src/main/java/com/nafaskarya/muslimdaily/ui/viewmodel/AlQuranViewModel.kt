package com.nafaskarya.muslimdaily.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nafaskarya.muslimdaily.ui.data.quran.Surah
import com.nafaskarya.muslimdaily.ui.repository.quran.AlQuranRepository
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Manages UI state and data logic for the Quran feature.
 */
class AlQuranViewModel(private val repository: AlQuranRepository) : ViewModel() {

    // Internal mutable state for this ViewModel
    private val _surahsState = MutableStateFlow<UiState<List<Surah>>>(UiState.Loading)

    /**
     * Public, read-only state flow observed by the UI.
     */
    val surahsState: StateFlow<UiState<List<Surah>>> = _surahsState.asStateFlow()

    init {
        // Fetch data immediately when the ViewModel is created.
        fetchSurahs()
    }

    /**
     * Triggers a data fetch for the list of all surahs from the repository.
     */
    fun fetchSurahs() {
        viewModelScope.launch {
            repository.getAllSurahs().collect { uiState ->
                _surahsState.value = uiState
            }
        }
    }
}

/**
 * Factory for creating [AlQuranViewModel] instances with a custom [AlQuranRepository] dependency.
 */
@Suppress("UNCHECKED_CAST")
class AlQuranViewModelFactory(private val repository: AlQuranRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlQuranViewModel::class.java)) {
            return AlQuranViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}