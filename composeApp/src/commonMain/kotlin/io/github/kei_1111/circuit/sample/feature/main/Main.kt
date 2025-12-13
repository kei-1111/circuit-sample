package io.github.kei_1111.circuit.sample.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.di.AppScope
import io.github.kei_1111.circuit.sample.feature.main.home.HomeScreen
import kotlinx.coroutines.NonCancellable.start
import org.jetbrains.compose.resources.painterResource

@CircuitInject(MainScreen::class, AppScope::class)
@Composable
fun Main(
    state: MainState,
    modifier: Modifier = Modifier,
) {
    val currentScreen = state.navItems[state.selectedIndex].screen
    val backStack = rememberSaveableBackStack(currentScreen)

    LaunchedEffect(state.selectedIndex) {
        val targetScreen = state.navItems[state.selectedIndex].screen
        if (backStack.topRecord?.screen != targetScreen) {
            backStack.popUntil { false }
            backStack.push(targetScreen)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
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
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding()),
            onNavEvent = { navEvent -> state.eventSink(MainEvent.ChildNav(navEvent)) }
        )
    }
}

@Composable
private fun MainBottomNavigationBar(
    navItems: List<BottomNavItem>,
    selectedIndex: Int,
    onSelectTab: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onSelectTab(index) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) }
            )
        }
    }
}

@Composable
@Preview
private fun MainPreview() {
    CircuitSampleTheme {
        Main(
            state = MainState(
                navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite),
                selectedIndex = 0,
                eventSink = {}
            )
        )
    }
}

@Composable
@Preview
private fun MainBottomNavigationBarPreview() {
    CircuitSampleTheme {
        Surface {
            MainBottomNavigationBar(
                navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite),
                selectedIndex = 0,
                onSelectTab = {},
            )
        }
    }
}