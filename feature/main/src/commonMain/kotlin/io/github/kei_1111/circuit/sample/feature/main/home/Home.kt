package io.github.kei_1111.circuit.sample.feature.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.HomeScreen
import io.github.kei_1111.circuit.sample.feature.main.home.component.HomeContent

@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
fun Home(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    HomeContent(
        state = state,
        modifier = modifier.fillMaxSize()
    )
}
