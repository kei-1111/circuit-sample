package io.github.kei_1111.circuit.sample.core.local

fun interface DataStorePathProducer {
    fun produce(fileName: String): String
}