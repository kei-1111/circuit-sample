package io.github.kei_1111.circuit.sample.feature.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.SettingsScreen
import io.github.kei_1111.circuit.sample.feature.settings.component.SettingsContent
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

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

    SettingsContent(
        state = state,
        snackbarHostState = snackbarHostState,
        modifier = modifier,
    )
}
