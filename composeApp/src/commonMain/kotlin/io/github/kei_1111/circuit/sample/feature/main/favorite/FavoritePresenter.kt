package io.github.kei_1111.circuit.sample.feature.main.favorite

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.CommonParcelize
import io.github.kei_1111.circuit.sample.di.AppScope

@CommonParcelize
object FavoriteScreen : Screen

data class FavoriteState(
    val eventSink: (FavoriteEvent) -> Unit
) : CircuitUiState

sealed interface FavoriteEvent : CircuitUiEvent

class FavoritePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<FavoriteState> {

    @CircuitInject(FavoriteScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): FavoritePresenter
    }

    @Composable
    override fun present(): FavoriteState {
        return FavoriteState(
            eventSink = { event ->
                when (event) {
                    else -> {}
                }
            }
        )
    }
}