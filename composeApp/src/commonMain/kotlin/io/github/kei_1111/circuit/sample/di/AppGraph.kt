package io.github.kei_1111.circuit.sample.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Includes
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepositoryImpl
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStore

@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class)
interface AppGraph {
    val userPreferencesRepository: UserPreferencesRepository

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Includes platformGraph: PlatformGraph): AppGraph
    }

    @SingleIn(AppScope::class)
    @Provides
    fun provideDataStore(dataStorePathProducer: DataStorePathProducer): DataStore<Preferences> =
        createDataStore(dataStorePathProducer)

    @SingleIn(AppScope::class)
    @Provides
    fun provideUserPreferencesRepository(
        dataStore: DataStore<Preferences>
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(dataStore)
}
