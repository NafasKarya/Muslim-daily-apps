package com.nafaskarya.muslimdaily.ui.repository.notification

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Inisialisasi DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    private val DAILY_REMINDER_KEY = booleanPreferencesKey("daily_reminder_enabled")

    // Flow untuk mendapatkan status reminder secara real-time
    val isDailyReminderEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DAILY_REMINDER_KEY] ?: false // Default-nya false
        }

    // Fungsi untuk mengubah status reminder
    suspend fun setDailyReminder(isEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DAILY_REMINDER_KEY] = isEnabled
        }
    }
}
