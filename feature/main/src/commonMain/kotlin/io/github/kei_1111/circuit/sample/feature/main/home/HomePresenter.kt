package io.github.kei_1111.circuit.sample.feature.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.HomeScreen

@AssistedInject
class HomePresenter : Presenter<HomeState> {

    @CircuitInject(HomeScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(): HomePresenter
    }

    @Composable
    override fun present(): HomeState {
        var count by rememberRetained { mutableStateOf(0) }

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
