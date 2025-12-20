package io.github.kei_1111.circuit.sample.feature.main.more

import com.slack.circuit.runtime.CircuitUiState

data class MoreState(
    val eventSink: (MoreEvent) -> Unit
) : CircuitUiState
