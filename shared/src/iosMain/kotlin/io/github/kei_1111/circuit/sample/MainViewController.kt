package io.github.kei_1111.circuit.sample

import androidx.compose.ui.window.ComposeUIViewController
import coil3.SingletonImageLoader
import io.github.kei_1111.circuit.sample.di.createIosAppGraph

@Suppress("FunctionNaming")
fun MainViewController() = ComposeUIViewController {
    val appGraph = createIosAppGraph()

    SingletonImageLoader.setSafe { appGraph.imageLoader }

    appGraph.app()
}
