package io.github.kei_1111.circuit.sample.feature.settings.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import circuit_sample.feature.settings.generated.resources.Res
import circuit_sample.feature.settings.generated.resources.ic_dark_mode
import circuit_sample.feature.settings.generated.resources.ic_light_mode
import circuit_sample.feature.settings.generated.resources.ic_system
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingsItem(
    userPreferences: UserPreferences,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
        when (userPreferences) {
            is UserPreferences.Theme -> {
                val icon = when (userPreferences) {
                    UserPreferences.Theme.SYSTEM -> Res.drawable.ic_system
                    UserPreferences.Theme.LIGHT -> Res.drawable.ic_light_mode
                    UserPreferences.Theme.DARK -> Res.drawable.ic_dark_mode
                }
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
            is UserPreferences.SeedColor -> {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(userPreferences.color)
                )
            }
        }
        Text(
            text = when (userPreferences) {
                UserPreferences.Theme.SYSTEM -> "端末のテーマ"
                UserPreferences.Theme.LIGHT -> "ライトテーマ"
                UserPreferences.Theme.DARK -> "ダークテーマ"
                UserPreferences.SeedColor.Default -> "デフォルト"
                is UserPreferences.SeedColor.Custom -> "カスタム"
            },
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
private fun SettingsItemPreview(
    @PreviewParameter(SettingsItemPPP::class) parameter: SettingsItemPreviewParameter
) {
    CircuitSampleTheme {
        Surface {
            SettingsItem(
                userPreferences = parameter.userPreferences,
                selected = parameter.selected,
                onClick = {}
            )
        }
    }
}

private data class SettingsItemPreviewParameter(
    val userPreferences: UserPreferences,
    val selected: Boolean,
)

private class SettingsItemPPP : CollectionPreviewParameterProvider<SettingsItemPreviewParameter>(
    collection = listOf(
        SettingsItemPreviewParameter(
            userPreferences = UserPreferences.Theme.SYSTEM,
            selected = true,
        ),
        SettingsItemPreviewParameter(
            userPreferences = UserPreferences.Theme.LIGHT,
            selected = false,
        ),
        SettingsItemPreviewParameter(
            userPreferences = UserPreferences.Theme.DARK,
            selected = false,
        ),
        SettingsItemPreviewParameter(
            userPreferences = UserPreferences.SeedColor.Default,
            selected = true,
        ),
        SettingsItemPreviewParameter(
            userPreferences = UserPreferences.SeedColor.Custom(Color.Red),
            selected = false,
        ),
    )
)
