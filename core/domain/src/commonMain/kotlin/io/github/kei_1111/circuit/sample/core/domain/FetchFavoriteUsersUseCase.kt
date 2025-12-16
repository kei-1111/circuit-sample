package io.github.kei_1111.circuit.sample.core.domain

import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.data.FavoriteUserRepository
import io.github.kei_1111.circuit.sample.core.model.User

class FetchFavoriteUsersUseCase @Inject constructor(
    private val favoriteUserRepository: FavoriteUserRepository,
) {
    suspend operator fun invoke(): List<User> =
        favoriteUserRepository.fetchFavoriteUsers()
}