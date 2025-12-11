package io.github.kei_1111.circuit.sample.feature.home

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

class HomePresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is HomeScreen -> HomePresenter()
            else -> null
        }
    }
}

class HomeUiFactory : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when(screen) {
            is HomeScreen -> ui<HomeState> { state, modifier -> Home(state, modifier) }
            else -> null
        }
    }
}
