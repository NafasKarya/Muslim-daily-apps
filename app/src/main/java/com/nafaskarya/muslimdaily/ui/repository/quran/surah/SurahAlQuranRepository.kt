package com.nafaskarya.muslimdaily.ui.repository.quran.surah

import com.nafaskarya.muslimdaily.ui.data.quran.surah.SurahDetail
import com.nafaskarya.muslimdaily.ui.remotes.api.quran.surah.SurahAlQuranApiService
import com.nafaskarya.muslimdaily.ui.utils.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for handling Surah detail data operations.
 * It abstracts the data source (API) from the ViewModels.
 */
class SurahAlQuranRepository(private val apiService: SurahAlQuranApiService) {

    /**
     * Fetches the complete details for a specific surah from the API,
     * including all its ayahs.
     *
     * @param surahNumber The number of the surah to fetch.
     * @return A Flow emitting UiState which contains a SurahDetail object on success.
     */
    fun getSurahDetail(surahNumber: Int): Flow<UiState<SurahDetail>> = flow {
        emit(UiState.Loading)
        try {
            val result = apiService.getSurahDetail(surahNumber)
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }
}

