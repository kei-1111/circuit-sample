package io.github.kei_1111.circuit.sample

import androidx.compose.ui.window.ComposeUIViewController
import io.github.kei_1111.circuit.sample.di.createIosAppGraph

fun MainViewController() = ComposeUIViewController {
    val appGraph = createIosAppGraph()
    appGraph.app()
}