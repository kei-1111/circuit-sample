package io.github.kei_1111.circuit.sample.core.domain

import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.model.UserPreferences

class SetColorUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke(color: UserPreferences.Color) {
        userPreferencesRepository.setColor(color)
    }
}