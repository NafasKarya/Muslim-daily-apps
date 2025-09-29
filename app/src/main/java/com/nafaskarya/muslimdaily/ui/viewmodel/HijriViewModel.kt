package com.nafaskarya.muslimdaily.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.nafaskarya.muslimdaily.ui.data.hijri.HijriDate
import com.nafaskarya.muslimdaily.ui.repository.hijri.HijriRepository
import com.nafaskarya.muslimdaily.ui.utils.state.UiState

/**
 * Manages UI state and data logic for the Hijri calendar feature.
 */
class HijriViewModel(private val repository: HijriRepository) : ViewModel() {

    // Internal mutable state for this ViewModel
    private val _hijriDateState = MutableStateFlow<UiState<HijriDate>>(UiState.Loading)

    /**
     * Public, read-only state flow observed by the UI.
     */
    val hijriDateState: StateFlow<UiState<HijriDate>> = _hijriDateState.asStateFlow()

    init {
        fetchHijriDate()
    }

    /**
     * Triggers a data fetch for the current Hijri date from the repository.
     * This function is safe to call multiple times (e.g., on pull-to-refresh).
     */
    fun fetchHijriDate() {
        viewModelScope.launch {
            repository.getTodayHijriDate().collect {
                _hijriDateState.value = it
            }
        }
    }
}

/**
 * Factory for creating [HijriViewModel] instances with a custom [HijriRepository] dependency.
 */
@Suppress("UNCHECKED_CAST")
class HijriViewModelFactory(private val repository: HijriRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HijriViewModel::class.java)) {
            return HijriViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

