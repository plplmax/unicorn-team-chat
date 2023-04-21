package com.github.plplmax.chat.user

import com.github.plplmax.chat.auth.Authorization
import kotlinx.serialization.Serializable

@Serializable
data class UnauthorizedUser(private val username: String, private val password: String) {
    suspend fun asAuthorized(authorization: Authorization): Result<User> {
        return authorization.authorized(username, password.toCharArray())
    }
}
