package com.github.plplmax.chat.auth

import com.github.plplmax.chat.user.User
import com.github.plplmax.chat.user.UserRepository

class AuthorizationImpl(private val repository: UserRepository) : Authorization {
    override suspend fun authorized(username: String, password: CharArray): Result<User> {
        val encodedPassword = EncodedPasswordOf(password)
        val exception = IllegalArgumentException("Incorrect username or password")
        return repository.userByUsername(username)?.let { user ->
            if (encodedPassword.matches(user.password)) {
                Result.success(user)
            } else {
                Result.failure(exception)
            }
        } ?: Result.failure(exception)
    }
}