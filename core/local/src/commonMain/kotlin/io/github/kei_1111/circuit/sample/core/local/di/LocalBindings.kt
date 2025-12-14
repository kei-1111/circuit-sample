package io.github.kei_1111.circuit.sample.core.local.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStore

@BindingContainer
object LocalBindings {
    @SingleIn(LocalScope::class)
    @Provides
    fun provideDataStore(
        dataStorePathProducer: DataStorePathProducer
    ): DataStore<Preferences> = createDataStore(dataStorePathProducer)
}