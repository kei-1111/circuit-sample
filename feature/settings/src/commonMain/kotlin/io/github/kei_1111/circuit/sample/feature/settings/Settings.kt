package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import circuit_sample.feature.settings.generated.resources.Res
import circuit_sample.feature.settings.generated.resources.color_section_title
import circuit_sample.feature.settings.generated.resources.save
import circuit_sample.feature.settings.generated.resources.theme_section_title
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.core.navigation.SettingsScreen
import io.github.kei_1111.circuit.sample.feature.settings.component.ColorPickerBottomSheet
import io.github.kei_1111.circuit.sample.feature.settings.component.SettingsSection
import io.github.kei_1111.circuit.sample.feature.settings.component.SettingsTopAppBar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(SettingsScreen::class, AppScope::class)
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
                scope.launch { snackbarHostState.showSnackbar(getString(effect.messageRes)) }
                state.eventSink(SettingsEvent.ClearSideEffect)
            }
            null -> {}
        }
    }

    CircuitSampleTheme(
        seedColor = state.seedColor,
        theme = state.theme
    ) {
        if (state.showColorPicker) {
            ColorPickerBottomSheet(
                initialColor = state.seedColor.color,
                onDismiss = { state.eventSink(SettingsEvent.HideColorPicker) },
                onChangeColor = { color ->
                    state.eventSink(SettingsEvent.UpdateSeedColor(UserPreferences.SeedColor.Custom(color)))
                },
            )
        }

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
                    title = stringResource(Res.string.theme_section_title),
                    items = UserPreferences.Theme.entries,
                    selectedItem = state.theme,
                    onClickItem = { state.eventSink(SettingsEvent.UpdateTheme(it)) }
                )
                SettingsSection(
                    title = stringResource(Res.string.color_section_title),
                    items = listOf(
                        UserPreferences.SeedColor.Default,
                        (state.seedColor as? UserPreferences.SeedColor.Custom)
                            ?: UserPreferences.SeedColor.Custom(Color(-6259457))
                    ),
                    selectedItem = state.seedColor,
                    onClickItem = { seedColor ->
                        when (seedColor) {
                            is UserPreferences.SeedColor.Default -> {
                                state.eventSink(SettingsEvent.UpdateSeedColor(seedColor))
                            }
                            is UserPreferences.SeedColor.Custom -> {
                                state.eventSink(SettingsEvent.ShowColorPicker)
                            }
                        }
                    }
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
                        text = stringResource(Res.string.save),
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
    CircuitSampleTheme {
        Settings(
            state = SettingsState(
                eventSink = {}
            )
        )
    }
}
