package io.github.kei_1111.circuit.sample.feature.main.more

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.ic_code
import circuit_sample.feature.main.generated.resources.ic_settings
import circuit_sample.feature.main.generated.resources.oss_licenses
import circuit_sample.feature.main.generated.resources.settings
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.navigation.MoreScreen
import io.github.kei_1111.circuit.sample.feature.main.more.component.MoreMenuItem
import io.github.kei_1111.circuit.sample.feature.main.more.component.MoreTopAppBar

@CircuitInject(MoreScreen::class, AppScope::class)
@Composable
fun More(
    state: MoreState,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier,
        topBar = { MoreTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MoreMenuItem(
                icon = Res.drawable.ic_settings,
                labelRes = Res.string.settings,
                onClick = { state.eventSink(MoreEvent.NavigateSettings) },
            )
            MoreMenuItem(
                icon = Res.drawable.ic_code,
                labelRes = Res.string.oss_licenses,
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
