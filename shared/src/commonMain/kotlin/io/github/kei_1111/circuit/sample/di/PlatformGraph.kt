package io.github.kei_1111.circuit.sample.di

import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer

interface PlatformGraph {
    val dataStorePathProducer: DataStorePathProducer
}