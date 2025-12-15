package io.github.kei_1111.circuit.sample.feature.main.home.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.app_name
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.app_name),
            )
        },
    )
}

@Composable
@Preview
private fun HomeTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            HomeTopAppBar()
        }
    }
}