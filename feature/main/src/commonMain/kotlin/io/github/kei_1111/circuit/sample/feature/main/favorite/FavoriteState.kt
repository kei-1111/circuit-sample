package io.github.kei_1111.circuit.sample.feature.main.favorite

import com.slack.circuit.runtime.CircuitUiState
import io.github.kei_1111.circuit.sample.core.model.User

sealed interface FavoriteState : CircuitUiState {
    data object Loading : FavoriteState

    data class Stable(
        val users: List<User>,
        val eventSink: (FavoriteEvent) -> Unit,
    ) : FavoriteState
}
