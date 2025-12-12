package io.github.kei_1111.circuit.sample.core.model

sealed interface UserPreferences {
    enum class Theme : UserPreferences {
        SYSTEM, LIGHT, DARK
    }
}