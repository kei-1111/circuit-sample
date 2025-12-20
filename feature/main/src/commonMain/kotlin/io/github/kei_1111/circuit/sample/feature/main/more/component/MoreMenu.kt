package io.github.kei_1111.circuit.sample.feature.main.more.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.ic_code
import circuit_sample.feature.main.generated.resources.ic_settings
import circuit_sample.feature.main.generated.resources.oss_licenses
import circuit_sample.feature.main.generated.resources.settings
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme

@Composable
internal fun MoreMenu(
    onClickSetting: () -> Unit,
    onClickOss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MoreMenuItem(
            icon = Res.drawable.ic_settings,
            labelRes = Res.string.settings,
            onClick = onClickSetting,
        )
        MoreMenuItem(
            icon = Res.drawable.ic_code,
            labelRes = Res.string.oss_licenses,
            onClick = onClickOss,
        )
    }
}

@Composable
@Preview
private fun MoreMenuPreview() {
    CircuitSampleTheme {
        Surface {
            MoreMenu(
                onClickSetting = {},
                onClickOss = {},
            )
        }
    }
}
