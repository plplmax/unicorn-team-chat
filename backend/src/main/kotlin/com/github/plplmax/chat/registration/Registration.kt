package com.github.plplmax.chat.registration

interface Registration {
    suspend fun registered(username: String, password: String, confirmedPassword: String): Result<Unit>
}