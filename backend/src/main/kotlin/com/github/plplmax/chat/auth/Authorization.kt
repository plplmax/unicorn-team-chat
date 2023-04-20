package com.github.plplmax.chat.auth

import com.github.plplmax.chat.user.User

interface Authorization {
    fun authorized(username: String, password: String): Result<User>
}