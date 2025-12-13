package io.github.kei_1111.circuit.sample.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.rememberDynamicColorScheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences

@Composable
fun CircuitSampleTheme(
    seedColor: UserPreferences.SeedColor = UserPreferences.SeedColor.Default,
    theme: UserPreferences.Theme = UserPreferences.Theme.SYSTEM,
    content: @Composable () -> Unit
) {
    val colorScheme = rememberDynamicColorScheme(
        seedColor = seedColor.color,
        isDark = when (theme) {
            UserPreferences.Theme.SYSTEM -> isSystemInDarkTheme()
            UserPreferences.Theme.LIGHT -> false
            UserPreferences.Theme.DARK -> true
        }
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
