package io.github.kei_1111.circuit.sample.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepositoryImpl

@BindingContainer
object DataBindings {
    @SingleIn(DataScope::class)
    @Provides
    fun provideUserPreferencesRepository(
        dataStore: DataStore<Preferences>
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(dataStore)
}