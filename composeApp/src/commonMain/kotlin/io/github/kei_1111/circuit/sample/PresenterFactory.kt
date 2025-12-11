package io.github.kei_1111.circuit.sample

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.github.kei_1111.circuit.sample.feature.home.HomePresenter
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen
import io.github.kei_1111.circuit.sample.feature.settings.SettingsPresenter
import io.github.kei_1111.circuit.sample.feature.settings.SettingsScreen

class PresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is HomeScreen -> HomePresenter(navigator)
            is SettingsScreen -> SettingsPresenter(navigator)
            else -> null
        }
    }
}