package io.github.kei_1111.circuit.sample.core.domain

import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import kotlinx.coroutines.flow.Flow

class GetColorUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(): Flow<UserPreferences.Color> =
        userPreferencesRepository.color
}