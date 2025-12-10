package io.github.kei_1111.circuit.sample.core.common

import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

actual typealias CommonParcelize = Parcelize

actual typealias CommonParceler<T> = Parceler<T>

actual typealias CommonTypeParceler<T, P> = TypeParceler<T, P>