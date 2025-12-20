package io.github.kei_1111.circuit.sample.feature.main.main

import com.slack.circuit.foundation.NavEvent
import com.slack.circuit.runtime.CircuitUiEvent

sealed interface MainEvent : CircuitUiEvent {
    data class SelectTab(val index: Int) : MainEvent
    data class ChildNav(val navEvent: NavEvent) : MainEvent
    data object NavigateBack : MainEvent
}
