package io.github.kei_1111.circuit.sample.feature.oss

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.OssScreen

data class OssState(
    val eventSink: (OssEvent) -> Unit
) : CircuitUiState

sealed interface OssEvent : CircuitUiEvent {
    data object NavigateBack : OssEvent
}

class OssPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<OssState> {

    @CircuitInject(OssScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): OssPresenter
    }

    @Composable
    override fun present(): OssState {
        return OssState(
            eventSink = { event ->
                when (event) {
                    OssEvent.NavigateBack -> navigator.pop()
                }
            }
        )
    }
}