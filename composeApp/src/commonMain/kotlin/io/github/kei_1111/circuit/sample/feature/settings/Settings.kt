package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSample
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.feature.settings.component.SettingsSection
import io.github.kei_1111.circuit.sample.feature.settings.component.SettingsTopAppBar
import kotlinx.coroutines.launch

@Composable
fun Settings(
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.sideEffect) {
        when (val effect = state.sideEffect) {
            is SettingsSideEffect.ShowSnackbar -> {
                scope.launch { snackbarHostState.showSnackbar(effect.message) }
                state.eventSink(SettingsEvent.ClearSideEffect)
            }
            null -> {}
        }
    }

    CircuitSample(theme = state.theme) {
        Scaffold(
            modifier = modifier,
            topBar = {
                SettingsTopAppBar(
                    onClickBack = { state.eventSink(SettingsEvent.NavigateBack) },
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SettingsSection(
                    title = "アプリ テーマ",
                    items = UserPreferences.Theme.entries,
                    selectedItem = state.theme,
                    onItemClick = { state.eventSink(SettingsEvent.UpdateTheme(it)) }
                )
                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    onClick = { state.eventSink(SettingsEvent.SaveSettings) },
                    enabled = state.canSave,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(48.dp)
                ) {
                    Text(
                        text = "保存",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
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
