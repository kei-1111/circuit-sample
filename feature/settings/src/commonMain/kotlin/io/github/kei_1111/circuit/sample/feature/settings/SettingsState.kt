package io.github.kei_1111.circuit.sample.feature.settings

import com.slack.circuit.runtime.CircuitUiState
import io.github.kei_1111.circuit.sample.core.model.UserPreferences

data class SettingsState(
    val theme: UserPreferences.Theme = UserPreferences.Theme.SYSTEM,
    val seedColor: UserPreferences.SeedColor = UserPreferences.SeedColor.Default,
    val showColorPicker: Boolean = false,
    val canSave: Boolean = false,
    val sideEffect: SettingsSideEffect? = null,
    val eventSink: (SettingsEvent) -> Unit,
) : CircuitUiState
