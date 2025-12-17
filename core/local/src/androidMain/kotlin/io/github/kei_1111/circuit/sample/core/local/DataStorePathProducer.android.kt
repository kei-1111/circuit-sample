package io.github.kei_1111.circuit.sample.core.local

import android.content.Context

fun createDataStorePathProducer(context: Context): DataStorePathProducer =
    DataStorePathProducer { fileName ->
        context.filesDir.resolve(fileName).absolutePath
    }
