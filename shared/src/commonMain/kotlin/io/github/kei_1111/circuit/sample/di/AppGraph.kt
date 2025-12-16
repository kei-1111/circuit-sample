package io.github.kei_1111.circuit.sample.di

import coil3.ImageLoader
import io.github.kei_1111.circuit.sample.CircuitSampleApp

/**
 * 共通のAppGraphインターフェース。
 * プラットフォーム固有の実装（AndroidAppGraph, IosAppGraph）で@DependencyGraphを定義する。
 *
 * iOS向けは compiler plugin が metadata only target をサポートしないため、
 * 手動で依存関係を定義する必要がある。
 * 参照: https://github.com/ZacSweers/metro/issues/460
 */
interface AppGraph {
    val app: CircuitSampleApp
    val imageLoader: ImageLoader
}
