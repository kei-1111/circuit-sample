package io.github.kei_1111.circuit.sample.feature.main.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.ic_code
import circuit_sample.feature.main.generated.resources.ic_settings
import circuit_sample.feature.main.generated.resources.oss_licenses
import circuit_sample.feature.main.generated.resources.settings
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.navigation.MoreScreen
import io.github.kei_1111.circuit.sample.feature.main.more.component.MoreMenuItem
import io.github.kei_1111.circuit.sample.feature.main.more.component.MoreTopAppBar
import org.jetbrains.compose.resources.stringResource

@CircuitInject(MoreScreen::class, AppScope::class)
@Composable
fun More(
    state: MoreState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { MoreTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MoreMenuItem(
                icon = Res.drawable.ic_settings,
                label = stringResource(Res.string.settings),
                onClick = { state.eventSink(MoreEvent.NavigateSettings) },
            )
            HorizontalDivider()
            MoreMenuItem(
                icon = Res.drawable.ic_code,
                label = stringResource(Res.string.oss_licenses),
                onClick = { state.eventSink(MoreEvent.NavigateOss) },
            )
        }
    }
}

@Composable
@Preview
private fun MorePreview() {
    CircuitSampleTheme {
        More(
            state = MoreState(
                eventSink = {}
            )
        )
    }
}