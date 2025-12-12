package io.github.kei_1111.circuit.sample

import android.app.Application
import dev.zacsweers.metro.createGraphFactory
import io.github.kei_1111.circuit.sample.di.AndroidPlatformGraph
import io.github.kei_1111.circuit.sample.di.AppGraph

class CircuitSampleApplication : Application() {
    val appGraph: AppGraph by lazy {
        val platformGraph = createGraphFactory<AndroidPlatformGraph.Factory>()
            .create(applicationContext)
        createGraphFactory<AppGraph.Factory>()
            .create(platformGraph)
    }
}