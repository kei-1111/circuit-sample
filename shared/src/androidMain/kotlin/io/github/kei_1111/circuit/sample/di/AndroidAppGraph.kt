package io.github.kei_1111.circuit.sample.di

import android.content.Context
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStorePathProducer

@DependencyGraph
interface AndroidPlatformGraph : PlatformGraph {

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides context: Context): AndroidPlatformGraph
    }

    @Provides
    fun provideDataStorePathProducer(context: Context): DataStorePathProducer =
        createDataStorePathProducer(context)
}