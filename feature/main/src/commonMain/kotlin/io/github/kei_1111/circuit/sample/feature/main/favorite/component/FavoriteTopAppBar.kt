package io.github.kei_1111.circuit.sample.feature.main.favorite.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.favorite
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.favorite),
            )
        }
    )
}

@Composable
@Preview
private fun FavoriteTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            FavoriteTopAppBar()
        }
    }
}
