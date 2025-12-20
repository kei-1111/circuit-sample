package io.github.kei_1111.circuit.sample.feature.main.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.MainScreen

class MainPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<MainState> {

    @CircuitInject(MainScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): MainPresenter
    }

    @Composable
    override fun present(): MainState {
        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

        return MainState(
            navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite, BottomNavItem.More),
            selectedIndex = selectedIndex,
            eventSink = { event ->
                when (event) {
                    is MainEvent.SelectTab -> selectedIndex = event.index
                    is MainEvent.ChildNav -> navigator.onNavEvent(event.navEvent)
                    MainEvent.NavigateBack -> {
                        if (selectedIndex == 0) {
                            // Homeタブではアプリを終了（popでonRootPopが呼ばれる）
                            navigator.pop()
                        } else {
                            // それ以外のタブではHomeタブに戻る
                            selectedIndex = 0
                        }
                    }
                }
            }
        )
    }
}
