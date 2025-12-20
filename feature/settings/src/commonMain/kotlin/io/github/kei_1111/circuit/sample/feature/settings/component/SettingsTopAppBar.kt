package io.github.kei_1111.circuit.sample.feature.settings.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.feature.settings.generated.resources.Res
import circuit_sample.feature.settings.generated.resources.ic_back
import circuit_sample.feature.settings.generated.resources.settings
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsTopAppBar(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.settings),
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onClickBack,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
@Preview
private fun SettingsTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            SettingsTopAppBar(
                onClickBack = {},
            )
        }
    }
}
