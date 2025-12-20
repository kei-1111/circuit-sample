package io.github.kei_1111.circuit.sample.feature.main.more

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.MoreScreen
import io.github.kei_1111.circuit.sample.feature.main.more.component.MoreContent

@CircuitInject(MoreScreen::class, AppScope::class)
@Composable
fun More(
    state: MoreState,
    modifier: Modifier = Modifier,
) {
    MoreContent(
        state = state,
        modifier = modifier.fillMaxSize(),
    )
}
