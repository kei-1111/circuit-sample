package io.github.kei_1111.circuit.sample.feature.settings

import org.jetbrains.compose.resources.StringResource

sealed interface SettingsSideEffect {
    data class ShowSnackbar(val messageRes: StringResource) : SettingsSideEffect
}
