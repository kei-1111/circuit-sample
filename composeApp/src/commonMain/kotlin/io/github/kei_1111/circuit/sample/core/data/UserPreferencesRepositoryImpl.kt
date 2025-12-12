package io.github.kei_1111.circuit.sample.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {
    private companion object {
        val THEME_CONFIG_KEY = stringPreferencesKey("theme_config")
    }

    override val theme: Flow<UserPreferences.Theme> = dataStore.data.map { preferences ->
        val themeString = preferences[THEME_CONFIG_KEY] ?: UserPreferences.Theme.SYSTEM.name
        UserPreferences.Theme.valueOf(themeString)
    }

    override suspend fun setTheme(themeConfig: UserPreferences.Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_CONFIG_KEY] = themeConfig.name
        }
    }
}