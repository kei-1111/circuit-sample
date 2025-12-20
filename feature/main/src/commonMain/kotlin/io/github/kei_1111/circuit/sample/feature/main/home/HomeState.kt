package io.github.kei_1111.circuit.sample.feature.main.home

import com.slack.circuit.runtime.CircuitUiState

data class HomeState(
    val count: Int,
    val eventSink: (HomeEvent) -> Unit
) : CircuitUiState
