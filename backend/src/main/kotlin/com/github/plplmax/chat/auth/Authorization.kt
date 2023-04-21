package com.github.plplmax.chat.auth

import com.github.plplmax.chat.user.User

interface Authorization {
    suspend fun authorized(username: String, password: CharArray): Result<User>
}