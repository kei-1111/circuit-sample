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
        val COLOR_TYPE_KEY = stringPreferencesKey("color_type")
        val COLOR_ARGB_KEY = longPreferencesKey("color_argb")

        const val COLOR_TYPE_DEFAULT = "default"
        const val COLOR_TYPE_CUSTOM = "custom"
    }

    override val theme: Flow<UserPreferences.Theme> = dataStore.data.map { preferences ->
        val themeString = preferences[THEME_KEY] ?: UserPreferences.Theme.SYSTEM.name
        UserPreferences.Theme.valueOf(themeString)
    }

    override val color: Flow<UserPreferences.Color> = dataStore.data.map { preferences ->
        when (preferences[COLOR_TYPE_KEY]) {
            COLOR_TYPE_CUSTOM -> {
                val argb = preferences[COLOR_ARGB_KEY] ?: 0xFF0700FF
                UserPreferences.Color.Custom(argb)
            }
            else -> UserPreferences.Color.Default
        }
    }

    override suspend fun setTheme(theme: UserPreferences.Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }

    override suspend fun setColor(color: UserPreferences.Color) {
        dataStore.edit { preferences ->
            when (color) {
                is UserPreferences.Color.Default -> {
                    preferences[COLOR_TYPE_KEY] = COLOR_TYPE_DEFAULT
                    preferences.remove(COLOR_ARGB_KEY)
                }
                is UserPreferences.Color.Custom -> {
                    preferences[COLOR_TYPE_KEY] = COLOR_TYPE_CUSTOM
                    preferences[COLOR_ARGB_KEY] = color.argb
                }
            }
        }
    }
}