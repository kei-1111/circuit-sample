package io.github.kei_1111.circuit.sample.core.model

import androidx.compose.ui.graphics.Color

sealed interface UserPreferences {
    enum class Theme : UserPreferences {
        SYSTEM, LIGHT, DARK
    }

    sealed interface SeedColor : UserPreferences {
        val color: Color

        data object Default : SeedColor { override val color: Color = Color(0xFF6750A4) }
        data class Custom(override val color: Color) : SeedColor
    }
}