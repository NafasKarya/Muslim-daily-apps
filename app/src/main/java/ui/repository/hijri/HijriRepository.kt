// Lokasi file: app/src/main/java/com/packageanda/data/repository/HijriRepository.kt

package ui.repository.hijri

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ui.data.hijri.HijriDate
import ui.remotes.api.hijri.HijriCalendarApiService
import ui.utils.state.UiState

// Nama class Repository juga dibuat lebih spesifik
class HijriRepository(private val apiService: HijriCalendarApiService) {

    fun getTodayHijriDate(): Flow<UiState<HijriDate>> = flow {
        emit(UiState.Loading)
        try {
            val result = apiService.getTodayHijriDate()
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "An unknown error occurred"))
        }
    }
}