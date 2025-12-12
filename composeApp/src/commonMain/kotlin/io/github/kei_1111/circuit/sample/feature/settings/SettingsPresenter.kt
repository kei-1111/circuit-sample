package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.common.CommonParcelize
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.di.AppScope
import kotlinx.coroutines.launch

@CommonParcelize
object SettingsScreen : Screen

sealed interface SettingsSideEffect {
    data class ShowSnackbar(val message: String) : SettingsSideEffect
}

data class SettingsState(
    val theme: UserPreferences.Theme = UserPreferences.Theme.SYSTEM,
    val canSave: Boolean = false,
    val sideEffect: SettingsSideEffect? = null,
    val eventSink: (SettingsEvent) -> Unit,
) : CircuitUiState

sealed interface SettingsEvent : CircuitUiEvent {
    data object NavigateBack : SettingsEvent
    data object ClearSideEffect : SettingsEvent
    data object SaveSettings : SettingsEvent
    data class UpdateTheme(val theme: UserPreferences.Theme) : SettingsEvent
}

@CircuitInject(SettingsScreen::class, AppScope::class)
class SettingsPresenter @Inject constructor(
    @Assisted private val navigator: Navigator,
    private val userPreferencesRepository: UserPreferencesRepository,
) : Presenter<SettingsState> {
    @Composable
    override fun present(): SettingsState {
        val scope = rememberCoroutineScope()
        var sideEffect by remember { mutableStateOf<SettingsSideEffect?>(null) }
        val savedTheme by userPreferencesRepository.theme.collectAsState(initial = UserPreferences.Theme.SYSTEM)
        var editingTheme by remember(savedTheme) { mutableStateOf(savedTheme) }

        return SettingsState(
            theme = editingTheme,
            canSave = savedTheme != editingTheme,
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
                            userPreferencesRepository.setTheme(editingTheme)
                            sideEffect = SettingsSideEffect.ShowSnackbar("設定を保存しました")
                        }
                    }
                    is SettingsEvent.UpdateTheme -> {
                        editingTheme = event.theme
                    }
                }
            }
        )
    }
}