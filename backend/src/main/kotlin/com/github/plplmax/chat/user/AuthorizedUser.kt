package com.github.plplmax.chat.user

import io.ktor.server.auth.*

data class AuthorizedUser(val id: Int) : Principal
