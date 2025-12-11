package io.github.kei_1111.circuit.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.retained.LocalRetainedStateRegistry
import com.slack.circuit.retained.rememberRetainedStateRegistry
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSample
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App() {
    val circuit = Circuit.Builder()
        .addPresenterFactory(PresenterFactory())
        .addUiFactory(UiFactory())
        .build()
    val backStack = rememberSaveableBackStack(HomeScreen)
    val navigator = rememberCircuitNavigator(backStack) {}

    val retainedStateRegistry = rememberRetainedStateRegistry()

    CircuitSample {
        CircuitCompositionLocals(
            circuit = circuit,
            retainedStateRegistry = retainedStateRegistry,
        ) {
            NavigableCircuitContent(navigator, backStack)
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}