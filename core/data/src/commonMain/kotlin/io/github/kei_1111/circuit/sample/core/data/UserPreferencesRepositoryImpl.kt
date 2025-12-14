package io.github.kei_1111.circuit.sample.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {
    private companion object {
        val THEME_KEY = stringPreferencesKey("theme")
        val SEED_COLOR_ARGB_KEY = longPreferencesKey("seed_color_argb")
    }

    override val theme: Flow<UserPreferences.Theme> = dataStore.data.map { preferences ->
        val themeString = preferences[THEME_KEY] ?: UserPreferences.Theme.SYSTEM.name
        UserPreferences.Theme.valueOf(themeString)
    }

    override val customSeedColorArgb: Flow<Long?> = dataStore.data.map { preferences ->
        preferences[SEED_COLOR_ARGB_KEY]
    }

    override suspend fun setTheme(theme: UserPreferences.Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }

    override suspend fun setCustomSeedColorArgb(argb: Long?) {
        dataStore.edit { preferences ->
            if (argb != null) {
                preferences[SEED_COLOR_ARGB_KEY] = argb
            } else {
                preferences.remove(SEED_COLOR_ARGB_KEY)
            }
        }
    }
}