package io.github.kei_1111.circuit.sample

import android.app.Application
import io.github.kei_1111.circuit.sample.di.AndroidAppGraph
import io.github.kei_1111.circuit.sample.di.createAndroidAppGraph

class CircuitSampleApplication : Application() {
    val appGraph: AndroidAppGraph by lazy {
        createAndroidAppGraph(applicationContext)
    }
}
