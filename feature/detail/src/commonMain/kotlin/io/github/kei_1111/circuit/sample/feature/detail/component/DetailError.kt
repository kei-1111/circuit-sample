package io.github.kei_1111.circuit.sample.feature.detail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme

@Composable
internal fun DetailError(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "User not found",
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
@Preview
private fun DetailErrorPreview() {
    CircuitSampleTheme {
        Surface {
            DetailError()
        }
    }
}
