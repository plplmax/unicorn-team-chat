package com.github.plplmax.chat.user

import com.github.plplmax.chat.registration.Registration
import kotlinx.serialization.Serializable

@Serializable
data class UnregisteredUser(
    private val username: String,
    private val password: String,
    private val confirmedPassword: String,
) {
    suspend fun asRegistered(registration: Registration): Result<Unit> {
        return registration.registered(username.trim(), password, confirmedPassword)
    }
}
