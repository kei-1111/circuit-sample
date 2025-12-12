package io.github.kei_1111.circuit.sample.feature.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import circuit_sample.composeapp.generated.resources.Res
import circuit_sample.composeapp.generated.resources.ic_dark_mode
import circuit_sample.composeapp.generated.resources.ic_light_mode
import circuit_sample.composeapp.generated.resources.ic_system
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingItem(
    config: UserPreferences,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (icon, label) = when (config) {
        UserPreferences.Theme.SYSTEM -> Res.drawable.ic_system to "端末のテーマ"
        UserPreferences.Theme.LIGHT -> Res.drawable.ic_light_mode to "ライトテーマ"
        UserPreferences.Theme.DARK -> Res.drawable.ic_dark_mode to "ダークテーマ"
    }

    Row(
        modifier = modifier
            .clickable(
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(horizontal = 8.dp)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = selected,
            onClick = null,
        )
    }
}

@Composable
@Preview
private fun SettingItemPreview(
    @PreviewParameter(SettingsItemPPP::class) parameter: SettingsItemPreviewParameter
) {
    CircuitSampleTheme {
        Surface {
            SettingItem(
                config = parameter.config,
                selected = parameter.selected,
                onClick = {}
            )
        }
    }
}

private data class SettingsItemPreviewParameter(
    val config: UserPreferences,
    val selected: Boolean,
)

private class SettingsItemPPP : CollectionPreviewParameterProvider<SettingsItemPreviewParameter>(
    collection = listOf(
        SettingsItemPreviewParameter(
            config = UserPreferences.Theme.DARK,
            selected = true,
        ),
        SettingsItemPreviewParameter(
            config = UserPreferences.Theme.LIGHT,
            selected = false,
        )
    )
)