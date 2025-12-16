package io.github.kei_1111.circuit.sample.core.data

import io.github.kei_1111.circuit.sample.core.model.User

interface UserRepository {
    suspend fun fetchFavoriteUsers(): List<User>
}