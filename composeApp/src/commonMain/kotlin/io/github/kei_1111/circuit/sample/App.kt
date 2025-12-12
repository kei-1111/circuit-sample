package io.github.kei_1111.circuit.sample

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.di.AppGraph
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen

@Composable
fun App(appGraph: AppGraph) {
    val circuit = remember(appGraph) {
        Circuit.Builder()
            .addPresenterFactory(PresenterFactory(appGraph.userPreferencesRepository))
            .addUiFactory(UiFactory())
            .build()
    }
    val backStack = rememberSaveableBackStack(HomeScreen)
    val navigator = rememberCircuitNavigator(backStack) {}

    val themeConfig by appGraph.userPreferencesRepository.theme.collectAsState(UserPreferences.Theme.SYSTEM)

    CircuitSampleTheme(
        theme = themeConfig
    ) {
        CircuitCompositionLocals(circuit) {
            Surface { NavigableCircuitContent(navigator, backStack) }
        }
    }
}