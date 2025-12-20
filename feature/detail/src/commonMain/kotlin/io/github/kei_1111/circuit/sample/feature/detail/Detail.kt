package io.github.kei_1111.circuit.sample.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.DetailScreen
import io.github.kei_1111.circuit.sample.feature.detail.component.DetailContent

@CircuitInject(DetailScreen::class, AppScope::class)
@Composable
fun Detail(
    state: DetailState,
    modifier: Modifier = Modifier,
) {
    DetailContent(
        state = state,
        modifier = modifier
    )
}
