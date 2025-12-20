package io.github.kei_1111.circuit.sample.feature.main.favorite

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface FavoriteEvent : CircuitUiEvent {
    data class NavigateDetail(val userId: String) : FavoriteEvent
}
