package io.github.kei_1111.circuit.sample.di

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStorePathProducer

@DependencyGraph
interface IosPlatformGraph : PlatformGraph {

    @Provides
    fun provideDataStorePathProducer(): DataStorePathProducer =
        createDataStorePathProducer()
}