package io.github.kei_1111.circuit.sample.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.materialkolor.rememberDynamicColorScheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.core.ui.SystemBarEffect

@Composable
fun CircuitSampleTheme(
    seedColor: UserPreferences.SeedColor = UserPreferences.SeedColor.Default,
    theme: UserPreferences.Theme = UserPreferences.Theme.SYSTEM,
    content: @Composable () -> Unit
) {
    val isDarkTheme = when (theme) {
        UserPreferences.Theme.SYSTEM -> isSystemInDarkTheme()
        UserPreferences.Theme.LIGHT -> false
        UserPreferences.Theme.DARK -> true
    }

    val colorScheme = rememberDynamicColorScheme(
        seedColor = seedColor.color,
        isDark = isDarkTheme
    )

    SystemBarEffect(isDarkTheme = isDarkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
