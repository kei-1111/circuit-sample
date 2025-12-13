package io.github.kei_1111.circuit.sample.core.data

import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val theme: Flow<UserPreferences.Theme>
    suspend fun setTheme(theme: UserPreferences.Theme)
}