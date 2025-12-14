package io.github.kei_1111.circuit.sample.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import dev.zacsweers.metro.createGraphFactory
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepositoryImpl
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStore
import io.github.kei_1111.circuit.sample.core.local.createDataStorePathProducer
import io.github.kei_1111.circuit.sample.core.navigation.FavoriteScreen
import io.github.kei_1111.circuit.sample.core.navigation.HomeScreen
import io.github.kei_1111.circuit.sample.core.navigation.MainScreen
import io.github.kei_1111.circuit.sample.core.navigation.SettingsScreen
import io.github.kei_1111.circuit.sample.feature.main.Main
import io.github.kei_1111.circuit.sample.feature.main.MainPresenter
import io.github.kei_1111.circuit.sample.feature.main.MainState
import io.github.kei_1111.circuit.sample.feature.main.favorite.Favorite
import io.github.kei_1111.circuit.sample.feature.main.favorite.FavoritePresenter
import io.github.kei_1111.circuit.sample.feature.main.favorite.FavoriteState
import io.github.kei_1111.circuit.sample.feature.main.home.Home
import io.github.kei_1111.circuit.sample.feature.main.home.HomePresenter
import io.github.kei_1111.circuit.sample.feature.main.home.HomeState
import io.github.kei_1111.circuit.sample.feature.settings.Settings
import io.github.kei_1111.circuit.sample.feature.settings.SettingsPresenter
import io.github.kei_1111.circuit.sample.feature.settings.SettingsState

/**
 * iOS向けのDependencyGraph。
 *
 * iOS（Native）ターゲットでは compiler plugin が @ContributesIntoSet による
 * 自動集約をサポートしていないため、手動で Presenter.Factory と Ui.Factory を提供する。
 *
 * 参照: https://github.com/ZacSweers/metro/issues/460
 */
@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class)
interface IosAppGraph : AppGraph {

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(): IosAppGraph
    }

    @Provides
    fun provideDataStorePathProducer(): DataStorePathProducer =
        createDataStorePathProducer()

    @SingleIn(AppScope::class)
    @Provides
    fun provideDataStore(dataStorePathProducer: DataStorePathProducer): DataStore<Preferences> =
        createDataStore(dataStorePathProducer)

    @SingleIn(AppScope::class)
    @Provides
    fun provideUserPreferencesRepository(
        dataStore: DataStore<Preferences>
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(dataStore)

    // ============================================
    // Presenter.Factory - 手動で提供
    // ============================================

    @SingleIn(AppScope::class)
    @Provides
    fun providePresenterFactories(
        mainPresenterFactory: MainPresenter.Factory,
        homePresenterFactory: HomePresenter.Factory,
        favoritePresenterFactory: FavoritePresenter.Factory,
        settingsPresenterFactory: SettingsPresenter.Factory,
    ): Set<Presenter.Factory> = setOf(
        object : Presenter.Factory {
            override fun create(
                screen: Screen,
                navigator: Navigator,
                context: CircuitContext
            ): Presenter<*>? = when (screen) {
                MainScreen -> mainPresenterFactory.create(navigator)
                HomeScreen -> homePresenterFactory.create(navigator)
                FavoriteScreen -> favoritePresenterFactory.create(navigator)
                SettingsScreen -> settingsPresenterFactory.create(navigator)
                else -> null
            }
        }
    )

    // ============================================
    // Ui.Factory - 手動で提供
    // ============================================

    @SingleIn(AppScope::class)
    @Provides
    fun provideUiFactories(): Set<Ui.Factory> = setOf(
        Ui.Factory { screen, _ ->
            when (screen) {
                MainScreen -> ui<MainState> { state, modifier -> Main(state, modifier) }
                HomeScreen -> ui<HomeState> { state, modifier -> Home(state, modifier) }
                FavoriteScreen -> ui<FavoriteState> { state, modifier -> Favorite(state, modifier) }
                SettingsScreen -> ui<SettingsState> { state, modifier -> Settings(state, modifier) }
                else -> null
            }
        }
    )

    // ============================================
    // Circuit
    // ============================================

    @SingleIn(AppScope::class)
    @Provides
    fun provideCircuit(
        presenterFactories: Set<Presenter.Factory>,
        uiFactories: Set<Ui.Factory>
    ): Circuit = Circuit.Builder()
        .addPresenterFactories(presenterFactories)
        .addUiFactories(uiFactories)
        .build()
}

fun createIosAppGraph(): IosAppGraph = createGraphFactory<IosAppGraph.Factory>().create()