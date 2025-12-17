package io.github.kei_1111.circuit.sample.core.ui

import androidx.compose.runtime.Composable

@Composable
@Suppress("UNUSED_PARAMETER")
actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) {
    // iOSではシステムバックボタンがないため何もしない
}
