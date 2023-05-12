package com.github.plplmax.chat.registration

import com.github.plplmax.chat.auth.EncodedPasswordOf
import com.github.plplmax.chat.user.UserRepository

class RegistrationImpl(private val repository: UserRepository) : Registration {
    override suspend fun registered(username: String, password: String, confirmedPassword: String): Result<Unit> {
        val validation = runCatching {
            validateOnEmpty("Username", username)
            validateOnEmpty("Password", password)

            validateLength("Username", username, 4..12)
            validateLength("Password", password, 6..8)

            require(confirmedPassword == password) { "Password and confirmed password do not match" }
        }

        return validation.onSuccess {
            repository.userByUsername(username)?.let {
                Result.failure(IllegalStateException("User with such username already exists"))
            } ?: run {
                repository.createdUser(username, EncodedPasswordOf(password.toCharArray()).value)
                Result.success(Unit)
            }
        }.onFailure {
            Result.failure<Unit>(it)
        }
    }

    private fun validateOnEmpty(label: String, input: String) {
        require(input.isNotBlank()) { "$label is required" }
    }

    private fun validateLength(label: String, input: String, range: IntRange) {
        require(input.length in range) { "$label should be from ${range.first} to ${range.last} characters" }
    }
}