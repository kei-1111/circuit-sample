package io.github.kei_1111.circuit.sample.feature.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences

@Composable
fun <T : UserPreferences> SettingsSection(
    title: String,
    items: List<T>,
    selectedItem: T,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
        )
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    SettingItem(
                        config = item,
                        selected = item == selectedItem,
                        onClick = { onItemClick(item) }
                    )
                    if (index != items.lastIndex) {
                        HorizontalDivider(modifier = Modifier.padding(start = 40.dp))
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun SettingsSectionPreview() {
    CircuitSampleTheme {
        Surface {
            SettingsSection(
                title = "アプリ テーマ",
                items = UserPreferences.Theme.entries,
                selectedItem = UserPreferences.Theme.SYSTEM,
                onItemClick = {}
            )
        }
    }
}