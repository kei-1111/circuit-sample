package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.feature.settings.component.SettingsTopAppBar

@Composable
fun Settings(
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SettingsTopAppBar(
                onClickBack = { state.eventSink(SettingsEvent.NavigateBack) },
            )
        }
    ) {

    }
}


@Composable
@Preview
private fun SettingsPreview() {
    Settings(
        state = SettingsState(
            eventSink = {}
        )
    )
}
