package io.github.kei_1111.circuit.sample.feature.main.home

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface HomeEvent : CircuitUiEvent {
    data object Increase : HomeEvent
    data object Decrease : HomeEvent
}
