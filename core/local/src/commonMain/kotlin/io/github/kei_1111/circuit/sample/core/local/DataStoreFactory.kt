package io.github.kei_1111.circuit.sample.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

internal const val DATA_STORE_FILE_NAME = "user_preferences.preferences_pb"

fun createDataStore(pathProducer: DataStorePathProducer): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { pathProducer.produce(DATA_STORE_FILE_NAME).toPath() }
    )
