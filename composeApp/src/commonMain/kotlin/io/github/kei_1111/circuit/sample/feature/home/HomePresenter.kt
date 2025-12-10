package io.github.kei_1111.circuit.sample.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.github.kei_1111.circuit.sample.core.common.CommonParcelize

@CommonParcelize
object HomeScreen : Screen

data class HomeUiState(
    val count: Int,
    val eventSink: (HomeUiEvent) -> Unit
) : CircuitUiState

sealed interface HomeUiEvent : CircuitUiEvent {
    data object Increase : HomeUiEvent
    data object Decrease : HomeUiEvent
}

class HomePresenter : Presenter<HomeUiState> {
    @Composable
    override fun present(): HomeUiState {
        var count by remember { mutableIntStateOf(0) }

        return HomeUiState(
            count = count,
            eventSink = { event ->
                when (event) {
                    HomeUiEvent.Decrease -> count--
                    HomeUiEvent.Increase -> count++
                }
            }
        )
    }
}
