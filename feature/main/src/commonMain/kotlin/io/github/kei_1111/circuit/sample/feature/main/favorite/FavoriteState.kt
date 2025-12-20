package io.github.kei_1111.circuit.sample.feature.main.favorite

import com.slack.circuit.runtime.CircuitUiState
import io.github.kei_1111.circuit.sample.core.model.User

data class FavoriteState(
    val isLoading: Boolean = true,
    val users: List<User> = emptyList(),
    val eventSink: (FavoriteEvent) -> Unit,
) : CircuitUiState
