package io.github.kei_1111.circuit.sample.feature.main.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.domain.FetchFavoriteUsersUseCase
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.core.navigation.DetailScreen
import io.github.kei_1111.circuit.sample.core.navigation.FavoriteScreen

data class FavoriteState(
    val isLoading: Boolean = true,
    val users: List<User> = emptyList(),
    val eventSink: (FavoriteEvent) -> Unit,
) : CircuitUiState

sealed interface FavoriteEvent : CircuitUiEvent {
    data class NavigateDetail(val userId: String) : FavoriteEvent
}

class FavoritePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val fetchFavoriteUsersUseCase: FetchFavoriteUsersUseCase,
) : Presenter<FavoriteState> {

    @CircuitInject(FavoriteScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): FavoritePresenter
    }

    @Composable
    override fun present(): FavoriteState {
        var isLoading by rememberRetained { mutableStateOf(true) }
        var users by rememberRetained { mutableStateOf<List<User>>(emptyList()) }

        LaunchedEffect(Unit) {
            users = fetchFavoriteUsersUseCase()
            isLoading = false
        }

        return FavoriteState(
            isLoading = isLoading,
            users = users,
            eventSink = { event ->
                when (event) {
                    is FavoriteEvent.NavigateDetail -> navigator.goTo(DetailScreen(event.userId))
                }
            }
        )
    }
}