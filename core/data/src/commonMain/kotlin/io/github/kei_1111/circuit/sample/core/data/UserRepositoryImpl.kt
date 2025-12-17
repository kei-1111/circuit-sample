@file:Suppress("MagicNumber")

package io.github.kei_1111.circuit.sample.core.data

import io.github.kei_1111.circuit.sample.core.model.User
import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {

    private val users = (1..100).map { index ->
        User(
            id = "user_$index",
            profileImageUrl = "https://avatars.githubusercontent.com/u/120158756",
            name = "No.$index",
        )
    }

    override suspend fun fetchFavoriteUsers(): List<User> {
        delay(1000)
        return users
    }

    override suspend fun fetchUser(userId: String): User? {
        return users.find { it.id == userId }
    }
}
