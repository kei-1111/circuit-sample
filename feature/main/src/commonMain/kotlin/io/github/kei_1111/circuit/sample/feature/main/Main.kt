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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.navigation.MainScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@CircuitInject(MainScreen::class, AppScope::class)
@Composable
fun Main(
    state: MainState,
    modifier: Modifier = Modifier,
) {
    val currentScreen = state.navItems[state.selectedIndex].screen

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
                        contentDescription = stringResource(item.labelRes)
                    )
                },
                label = { Text(stringResource(item.labelRes)) }
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
                navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite, BottomNavItem.More),
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
                navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite, BottomNavItem.More),
                selectedIndex = 0,
                onSelectTab = {},
            )
        }
    }
}