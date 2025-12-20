package io.github.kei_1111.circuit.sample.feature.detail

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface DetailEvent : CircuitUiEvent {
    data object NavigateBack : DetailEvent
}
