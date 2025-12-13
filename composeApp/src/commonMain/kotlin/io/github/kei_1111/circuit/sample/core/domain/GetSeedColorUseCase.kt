package io.github.kei_1111.circuit.sample.core.domain

import androidx.compose.ui.graphics.Color
import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSeedColorUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(): Flow<UserPreferences.SeedColor> =
        userPreferencesRepository.customSeedColorArgb.map { argb ->
            if (argb != null) {
                UserPreferences.SeedColor.Custom(Color(argb))
            } else {
                UserPreferences.SeedColor.Default
            }
        }
}