package io.github.kei_1111.circuit.sample.feature.main.main.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.foundation.CircuitContent
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.feature.main.main.BottomNavItem
import io.github.kei_1111.circuit.sample.feature.main.main.MainEvent
import io.github.kei_1111.circuit.sample.feature.main.main.MainState

@Composable
internal fun MainContent(
    state: MainState,
    modifier: Modifier = Modifier
) {
    val currentScreen = state.navItems[state.selectedIndex].screen

    Scaffold(
        modifier = modifier,
        bottomBar = {
            MainBottomNavigationBar(
                navItems = state.navItems,
                selectedIndex = state.selectedIndex,
                onSelectTab = { index -> state.eventSink(MainEvent.SelectTab(index)) }
            )
        }
    ) { innerPadding ->
        CircuitContent(
            screen = currentScreen,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            onNavEvent = { navEvent -> state.eventSink(MainEvent.ChildNav(navEvent)) }
        )
    }
}

@Composable
@Preview
private fun MainContentPreview() {
    CircuitSampleTheme {
        MainContent(
            state = MainState(
                navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite, BottomNavItem.More),
                selectedIndex = 0,
                eventSink = {}
            )
        )
    }
}
