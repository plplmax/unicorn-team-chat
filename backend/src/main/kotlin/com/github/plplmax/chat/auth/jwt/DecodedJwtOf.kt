package com.github.plplmax.chat.auth.jwt

import com.github.plplmax.chat.user.AuthorizedUser

class DecodedJwtOf(private val token: String) : DecodedJwt {
    override val user: AuthorizedUser by lazy {
        JwtConfig.verifier
            .verify(token)
            .let { AuthorizedUser(it.subject.toInt()) }
    }
}