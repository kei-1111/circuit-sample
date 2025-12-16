package io.github.kei_1111.circuit.sample

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import io.github.kei_1111.circuit.sample.di.AndroidAppGraph
import io.github.kei_1111.circuit.sample.di.createAndroidAppGraph

class CircuitSampleApplication : Application(), SingletonImageLoader.Factory {
    val appGraph: AndroidAppGraph by lazy {
        createAndroidAppGraph(applicationContext)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader = appGraph.imageLoader
}
