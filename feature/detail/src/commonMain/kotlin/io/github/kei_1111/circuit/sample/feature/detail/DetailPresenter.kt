package io.github.kei_1111.circuit.sample.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.domain.FetchUserUseCase
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.core.navigation.DetailScreen

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

sealed interface DetailEvent : CircuitUiEvent {
    data object NavigateBack : DetailEvent
}

class DetailPresenter @AssistedInject constructor(
    @Assisted private val screen: DetailScreen,
    @Assisted private val navigator: Navigator,
    private val fetchUserUseCase: FetchUserUseCase,
) : Presenter<DetailState> {

    @CircuitInject(DetailScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: DetailScreen, navigator: Navigator): DetailPresenter
    }

    @Composable
    override fun present(): DetailState {
        val user by produceState<User?>(null, screen.userId) {
            value = fetchUserUseCase(screen.userId)
        }

        val eventSink: (DetailEvent) -> Unit = { event ->
            when (event) {
                DetailEvent.NavigateBack -> navigator.pop()
            }
        }

        return when (user) {
            null -> DetailState.Loading
            else -> DetailState.Stable(
                user = user!!,
                eventSink = eventSink,
            )
        }
    }
}