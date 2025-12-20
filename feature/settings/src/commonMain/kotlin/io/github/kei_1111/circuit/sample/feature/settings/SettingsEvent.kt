package io.github.kei_1111.circuit.sample.feature.settings

import com.slack.circuit.runtime.CircuitUiEvent
import io.github.kei_1111.circuit.sample.core.model.UserPreferences

sealed interface SettingsEvent : CircuitUiEvent {
    data object NavigateBack : SettingsEvent
    data object ClearSideEffect : SettingsEvent
    data object SaveSettings : SettingsEvent
    data class UpdateTheme(val theme: UserPreferences.Theme) : SettingsEvent
    data class UpdateSeedColor(val seedColor: UserPreferences.SeedColor) : SettingsEvent
    data object ShowColorPicker : SettingsEvent
    data object HideColorPicker : SettingsEvent
}
