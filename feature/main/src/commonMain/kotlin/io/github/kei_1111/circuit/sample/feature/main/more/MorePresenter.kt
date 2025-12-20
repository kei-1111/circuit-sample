package io.github.kei_1111.circuit.sample.feature.main.more

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.MoreScreen
import io.github.kei_1111.circuit.sample.core.navigation.OssScreen
import io.github.kei_1111.circuit.sample.core.navigation.SettingsScreen

class MorePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<MoreState> {

    @CircuitInject(MoreScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): MorePresenter
    }

    @Composable
    override fun present(): MoreState {
        return MoreState(
            eventSink = { event ->
                when (event) {
                    MoreEvent.NavigateSettings -> navigator.goTo(SettingsScreen)
                    MoreEvent.NavigateOss -> navigator.goTo(OssScreen)
                }
            }
        )
    }
}
