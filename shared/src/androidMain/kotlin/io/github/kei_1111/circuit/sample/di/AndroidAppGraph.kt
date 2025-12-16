package io.github.kei_1111.circuit.sample.di

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import dev.zacsweers.metro.createGraphFactory
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.data.di.DataBindings
import io.github.kei_1111.circuit.sample.core.data.di.DataScope
import io.github.kei_1111.circuit.sample.core.local.DataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.createDataStorePathProducer
import io.github.kei_1111.circuit.sample.core.local.di.LocalBindings
import io.github.kei_1111.circuit.sample.core.local.di.LocalScope
import okio.Path.Companion.toOkioPath

private const val MAX_DISK_CACHE_SIZE = 100L * 1024L * 1024L // 100 MB

/**
 * Android向けのDependencyGraph。
 * @ContributesIntoSet による Factory の自動集約が機能する。
 */
@SingleIn(AppScope::class)
@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class, LocalScope::class],
    bindingContainers = [DataBindings::class, LocalBindings::class]
)
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
    fun provideImageLoader(context: Context): ImageLoader =
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, percent = 0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache").toOkioPath())
                    .maxSizeBytes(MAX_DISK_CACHE_SIZE)
                    .build()
            }
            .components { add(KtorNetworkFetcherFactory()) }
            .crossfade(true)
            .build()
}

fun createAndroidAppGraph(context: Context): AndroidAppGraph = createGraphFactory<AndroidAppGraph.Factory>().create(context)