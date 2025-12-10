package io.github.kei_1111.circuit.sample.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.rememberDynamicColorScheme

private val SeedColor = Color(0xFF0700FF)

@Composable
fun CircuitSample(
    seedColor: Color = SeedColor,
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = rememberDynamicColorScheme(
        seedColor = seedColor,
        isDark = isDark
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
