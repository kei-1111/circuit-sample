package io.github.kei_1111.circuit.sample.feature.main.more

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface MoreEvent : CircuitUiEvent {
    data object NavigateSettings : MoreEvent
    data object NavigateOss : MoreEvent
}
