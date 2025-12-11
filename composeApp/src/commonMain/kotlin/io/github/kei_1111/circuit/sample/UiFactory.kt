package io.github.kei_1111.circuit.sample

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import io.github.kei_1111.circuit.sample.feature.home.Home
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen
import io.github.kei_1111.circuit.sample.feature.home.HomeState
import io.github.kei_1111.circuit.sample.feature.settings.Settings
import io.github.kei_1111.circuit.sample.feature.settings.SettingsScreen
import io.github.kei_1111.circuit.sample.feature.settings.SettingsState

class UiFactory : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when(screen) {
            is HomeScreen -> ui<HomeState> { state, modifier -> Home(state, modifier) }
            is SettingsScreen -> ui<SettingsState> { state, modifier -> Settings(state, modifier) }
            else -> null
        }
    }
}
