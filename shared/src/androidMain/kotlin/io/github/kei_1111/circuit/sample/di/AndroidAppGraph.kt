package io.github.kei_1111.circuit.sample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepositoryImpl
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStore
import io.github.kei_1111.circuit.sample.core.local.createDataStorePathProducer
import dev.zacsweers.metro.createGraphFactory

/**
 * Android向けのDependencyGraph。
 * @ContributesIntoSet による Factory の自動集約が機能する。
 */
@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class)
interface AndroidAppGraph : AppGraph {

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides context: Context): AndroidAppGraph
    }

    @SingleIn(AppScope::class)
    @Provides
    fun provideCircuit(
        presenterFactories: Set<Presenter.Factory>,
        uiFactories: Set<Ui.Factory>
    ): Circuit = Circuit.Builder()
        .addPresenterFactories(presenterFactories)
        .addUiFactories(uiFactories)
        .build()

    @Provides
    fun provideDataStorePathProducer(context: Context): DataStorePathProducer =
        createDataStorePathProducer(context)

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

fun createAndroidAppGraph(context: Context): AndroidAppGraph = createGraphFactory<AndroidAppGraph.Factory>().create(context)