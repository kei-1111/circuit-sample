package io.github.kei_1111.circuit.sample.feature.oss

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.OssScreen
import io.github.kei_1111.circuit.sample.feature.oss.component.OssContent

@CircuitInject(OssScreen::class, AppScope::class)
@Composable
fun Oss(
    state: OssState,
    modifier: Modifier = Modifier,
) {
    OssContent(
        state = state,
        modifier = modifier
    )
}
