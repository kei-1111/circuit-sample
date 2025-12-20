package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import circuit_sample.feature.settings.generated.resources.Res
import circuit_sample.feature.settings.generated.resources.settings_saved
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.domain.GetSeedColorUseCase
import io.github.kei_1111.circuit.sample.core.domain.GetThemeUseCase
import io.github.kei_1111.circuit.sample.core.domain.SetSeedColorUseCase
import io.github.kei_1111.circuit.sample.core.domain.SetThemeUseCase
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.core.navigation.SettingsScreen
import kotlinx.coroutines.launch

class SettingsPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val getSeedColorUseCase: GetSeedColorUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val setSeedColorUseCase: SetSeedColorUseCase,
    private val setThemeUseCase: SetThemeUseCase,
) : Presenter<SettingsState> {

    @CircuitInject(SettingsScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): SettingsPresenter
    }

    @Composable
    override fun present(): SettingsState {
        val scope = rememberCoroutineScope()
        var sideEffect by remember { mutableStateOf<SettingsSideEffect?>(null) }
        var showColorPicker by remember { mutableStateOf(false) }

        val savedTheme by getThemeUseCase().collectAsState(initial = UserPreferences.Theme.SYSTEM)
        var editingTheme by remember(savedTheme) { mutableStateOf(savedTheme) }

        val savedSeedColor by getSeedColorUseCase().collectAsState(initial = UserPreferences.SeedColor.Default)
        var editingSeedColor by remember(savedSeedColor) { mutableStateOf(savedSeedColor) }

        val canSave = savedTheme != editingTheme || savedSeedColor != editingSeedColor

        return SettingsState(
            theme = editingTheme,
            seedColor = editingSeedColor,
            showColorPicker = showColorPicker,
            canSave = canSave,
            sideEffect = sideEffect,
            eventSink = { event ->
                when (event) {
                    SettingsEvent.NavigateBack -> {
                        navigator.pop()
                    }
                    SettingsEvent.ClearSideEffect -> {
                        sideEffect = null
                    }
                    SettingsEvent.SaveSettings -> {
                        scope.launch {
                            setThemeUseCase(editingTheme)
                            setSeedColorUseCase(editingSeedColor)
                            sideEffect = SettingsSideEffect.ShowSnackbar(Res.string.settings_saved)
                        }
                    }
                    is SettingsEvent.UpdateTheme -> {
                        editingTheme = event.theme
                    }
                    is SettingsEvent.UpdateSeedColor -> {
                        editingSeedColor = event.seedColor
                    }
                    SettingsEvent.ShowColorPicker -> {
                        showColorPicker = true
                    }
                    SettingsEvent.HideColorPicker -> {
                        showColorPicker = false
                    }
                }
            }
        )
    }
}
