package io.github.kei_1111.circuit.sample.feature.main.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.MainScreen
import io.github.kei_1111.circuit.sample.core.ui.PlatformBackHandler
import io.github.kei_1111.circuit.sample.feature.main.main.component.MainContent

@CircuitInject(MainScreen::class, AppScope::class)
@Composable
fun Main(
    state: MainState,
    modifier: Modifier = Modifier,
) {
    // システムバックボタンをハンドリング
    PlatformBackHandler { state.eventSink(MainEvent.NavigateBack) }

    MainContent(
        state = state,
        modifier = modifier.fillMaxSize()
    )
}
