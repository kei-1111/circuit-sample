package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.github.kei_1111.circuit.sample.core.common.CommonParcelize

@CommonParcelize
object SettingsScreen : Screen

data class SettingsState(
    val eventSink: (SettingsEvent) -> Unit,
) : CircuitUiState

sealed interface SettingsEvent : CircuitUiEvent {
    data object NavigateBack : SettingsEvent
}

class SettingsPresenter(
    val navigator: Navigator
) : Presenter<SettingsState> {
    @Composable
    override fun present(): SettingsState {
        return SettingsState(
            eventSink = { event ->
                when(event) {
                    SettingsEvent.NavigateBack -> navigator.pop()
                }
            }
        )
    }
}
