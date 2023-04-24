package com.github.plplmax.chat.auth.jwt

import com.github.plplmax.chat.user.AuthorizedUser

interface DecodedJwt {
    val user: AuthorizedUser
}