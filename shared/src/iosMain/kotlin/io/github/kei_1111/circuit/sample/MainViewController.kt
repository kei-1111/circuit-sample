package io.github.kei_1111.circuit.sample

import androidx.compose.ui.window.ComposeUIViewController
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metro.createGraphFactory
import io.github.kei_1111.circuit.sample.di.AppGraph
import io.github.kei_1111.circuit.sample.di.IosPlatformGraph

fun MainViewController() = ComposeUIViewController {
    val platformGraph = createGraph<IosPlatformGraph>()
    val appGraph = createGraphFactory<AppGraph.Factory>().create(platformGraph)
    appGraph.app()
}