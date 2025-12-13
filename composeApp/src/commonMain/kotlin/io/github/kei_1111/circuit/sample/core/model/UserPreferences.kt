package io.github.kei_1111.circuit.sample.core.model

sealed interface UserPreferences {
    enum class Theme : UserPreferences {
        SYSTEM, LIGHT, DARK
    }

    sealed interface Color : UserPreferences {
        data object Default : Color
        data class Custom(val argb: Long) : Color
    }
}