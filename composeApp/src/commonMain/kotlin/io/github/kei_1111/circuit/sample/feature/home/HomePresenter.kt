package io.github.kei_1111.circuit.sample.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.github.kei_1111.circuit.sample.core.common.CommonParcelize

@CommonParcelize
object HomeScreen : Screen

data class HomeState(
    val count: Int,
    val eventSink: (HomeEvent) -> Unit
) : CircuitUiState

sealed interface HomeEvent : CircuitUiEvent {
    data object Increase : HomeEvent
    data object Decrease : HomeEvent
}

class HomePresenter : Presenter<HomeState> {
    @Composable
    override fun present(): HomeState {
        var count by remember { mutableIntStateOf(0) }

        return HomeState(
            count = count,
            eventSink = { event ->
                when (event) {
                    HomeEvent.Decrease -> count--
                    HomeEvent.Increase -> count++
                }
            }
        )
    }
}
