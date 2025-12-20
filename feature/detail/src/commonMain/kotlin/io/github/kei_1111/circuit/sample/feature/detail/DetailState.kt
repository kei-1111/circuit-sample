package io.github.kei_1111.circuit.sample.feature.detail

import com.slack.circuit.runtime.CircuitUiState
import io.github.kei_1111.circuit.sample.core.model.User

sealed interface DetailState : CircuitUiState {
    val eventSink: (DetailEvent) -> Unit

    data object Loading : DetailState { override val eventSink: (DetailEvent) -> Unit = {} }

    data class Stable(
        val user: User,
        override val eventSink: (DetailEvent) -> Unit,
    ) : DetailState

    data class Error(
        override val eventSink: (DetailEvent) -> Unit,
    ) : DetailState
}
