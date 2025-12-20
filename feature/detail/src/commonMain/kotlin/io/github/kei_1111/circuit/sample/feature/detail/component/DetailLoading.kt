package io.github.kei_1111.circuit.sample.feature.detail.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme

@Composable
internal fun DetailLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier,
    )
}

@Composable
@Preview
private fun DetailLoadingPreview() {
    CircuitSampleTheme {
        Surface {
            DetailLoading()
        }
    }
}
