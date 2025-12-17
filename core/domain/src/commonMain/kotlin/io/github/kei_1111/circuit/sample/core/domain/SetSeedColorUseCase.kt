package io.github.kei_1111.circuit.sample.core.domain

import androidx.compose.ui.graphics.toArgb
import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.model.UserPreferences

class SetSeedColorUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke(seedColor: UserPreferences.SeedColor) {
        val argb = when (seedColor) {
            is UserPreferences.SeedColor.Custom -> seedColor.color.toArgb().toLong()
            is UserPreferences.SeedColor.Default -> null
        }
        userPreferencesRepository.setCustomSeedColorArgb(argb)
    }
}
