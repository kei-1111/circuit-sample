package io.github.kei_1111.circuit.sample.feature.main.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.ic_add
import circuit_sample.feature.main.generated.resources.ic_remove
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun HomeCounter(
    count: Int,
    onClickDecrease: () -> Unit,
    onClickIncrease: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onClickDecrease,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_remove),
                contentDescription = null
            )
        }
        Text(
            text = "$count",
            modifier = Modifier.widthIn(40.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
        IconButton(
            onClick = onClickIncrease,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_add),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
private fun HomeCounterPreview() {
    CircuitSampleTheme {
        Surface {
            HomeCounter(
                count = 10,
                onClickIncrease = {},
                onClickDecrease = {},
            )
        }
    }
}
