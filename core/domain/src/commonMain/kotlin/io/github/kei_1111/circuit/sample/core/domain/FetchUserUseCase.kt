package io.github.kei_1111.circuit.sample.core.domain

import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.data.UserRepository
import io.github.kei_1111.circuit.sample.core.model.User

class FetchUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userId: String): User? =
        userRepository.fetchUser(userId)
}