package io.github.kei_1111.circuit.sample

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import io.github.kei_1111.circuit.sample.core.navigation.Home
import io.github.kei_1111.circuit.sample.feature.home.homeEntry


@Composable
fun CircuitSampleNavDisplay() {
    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        onBack = backStack::removeLastOrNull,
        entryProvider = entryProvider {
            homeEntry()
        }
    )
}