package com.nafaskarya.muslimdaily.ui.repository.quran

import com.nafaskarya.muslimdaily.ui.data.quran.Surah
import com.nafaskarya.muslimdaily.ui.remotes.api.quran.AlQuranApiService
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for handling Quran-related data operations.
 * It abstracts the data source (API) from the rest of the app, like ViewModels.
 */
class AlQuranRepository(private val apiService: AlQuranApiService) {

    /**
     * Fetches a list of all surahs from the API and wraps the result in a UiState.
     * This function returns a Flow, which allows the UI to reactively observe
     * loading, success, and error states.
     *
     * @return A Flow emitting UiState which contains a List of Surah on success.
     */
    fun getAllSurahs(): Flow<UiState<List<Surah>>> = flow {
        // 1. Emit Loading state immediately to show a progress indicator on the UI.
        emit(UiState.Loading)
        try {
            // 2. Make the network call via the apiService.
            val result = apiService.getSurahs()
            // 3. If the call is successful, emit the Success state with the fetched data.
            emit(UiState.Success(result))
        } catch (e: Exception) {
            // 4. If any exception occurs (e.g., network error), emit the Error state.
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }
}