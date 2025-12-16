package io.github.kei_1111.circuit.sample.feature.detail.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import circuit_sample.feature.detail.generated.resources.Res
import circuit_sample.feature.detail.generated.resources.ic_back
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = "Detail") },
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "Back",
                )
            }
        },
    )
}

@Composable
@Preview
private fun DetailTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            DetailTopAppBar(
                onClickBack = {}
            )
        }
    }
}
