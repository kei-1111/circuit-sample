package io.github.kei_1111.circuit.sample.feature.oss.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.feature.oss.generated.resources.Res
import circuit_sample.feature.oss.generated.resources.ic_back
import circuit_sample.feature.oss.generated.resources.oss_licenses
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OssTopAppBar(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(Res.string.oss_licenses))
        },
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
@Preview
private fun OssTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            OssTopAppBar(
                onClickBack = {}
            )
        }
    }
}
